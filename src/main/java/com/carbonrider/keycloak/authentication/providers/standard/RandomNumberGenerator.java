package com.carbonrider.keycloak.authentication.providers.standard;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.authenticators.browser.AbstractUsernameFormAuthenticator;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class RandomNumberGenerator extends AbstractUsernameFormAuthenticator implements Authenticator {

    private static final Logger logger = Logger.getLogger(RandomNumberGenerator.class);

    private KeycloakSession session;

    public RandomNumberGenerator(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        MultivaluedMap<String, String> inputData = context.getHttpRequest().getDecodedFormParameters();

        String otp = inputData.getFirst("otp");

        logger.info("Entered OTP is " + otp);
        context.success();
    }

    @Override
    protected Response createLoginForm(LoginFormsProvider form) {
        return form.createLoginTotp();
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {

        logger.info("Starting custom authentication");
        Response challengeResponse = challenge(context, null);
        context.challenge(challengeResponse);
    }

    @Override
    public boolean requiresUser() {
        return true;
    }

    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

}
