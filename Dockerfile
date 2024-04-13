FROM keycloak/keycloak:22.0.1

USER root

WORKDIR /opt/keycloak

RUN keytool -genkeypair -storepass password -storetype PKCS12 -keyalg RSA -keysize 2048 -dname "CN=server" -alias server -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -keystore conf/server.keystore

COPY target/keycloak-quarkus-plugin.jar /opt/keycloak/providers/

ENV KC_PROXY=reencrypt

ENV KEYCLOAK_PROXY_ADDRESS_FORWARDING=true

RUN /opt/keycloak/bin/kc.sh build

USER 1000

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start", "--hostname-strict=false", "--optimized"]
