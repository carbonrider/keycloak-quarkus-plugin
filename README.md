Please refer to following steps to install this plugin.

**Compile**

This project maven build tool to build the Java project. Please make sure that maven is available on PATH variable. Use following command to compile the source code.

``mvn compile``

This should compile all the source file and display any errors present in the source code.

**Generate JAR**

If the previous step succeeds without any compilation error, execute following command to create the plugin JAR file.

``mvn package``

This should generate a JAR file and place it inside target folder.

**Generate DOCKER image**

A docker file is bundled along with this project to download the required keycloak version. The JAR file generated in the previous step will be placed inside providers directory. To generate the DOCKER image execute following command.

``docker build -t blog-kc:latest .``

**Run Container**

Finally you can run the Keycloak image using following command.

``docker run -p 8443:8443 -p 9080:8080 --name kc_blog -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin --rm blog-kc``