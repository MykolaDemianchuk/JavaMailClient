package com.demianchuk.service;

import org.apache.log4j.Logger;

import javax.mail.*;

public class SignInService {

    private static final Logger LOGGER = Logger.getLogger(SignInService.class);

    private SignInService() {
    }

    public static void signIn(Session session) throws Exception {

        try {
            session.getTransport().connect();
        } catch (AuthenticationFailedException e) {
            throw new Exception("Invalid credentials!");
        } catch (MessagingException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
