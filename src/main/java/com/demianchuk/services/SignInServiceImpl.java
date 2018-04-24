package com.demianchuk.services;

import javax.mail.*;
import org.apache.log4j.Logger;
import com.demianchuk.model.MailClient;

public class SignInServiceImpl implements SignInService {
    private static final Logger LOGGER = Logger.getLogger(SendEmailServiceImpl.class);

    @Override
    public void signIn(MailClient client, String username, char[] password) throws Exception{
        client.setAuthenticator(username, password);
        client.createSession();
        try {
            client.getTransport().connect();
        } catch (AuthenticationFailedException e) {
            throw new Exception("Invalid credentials!");
        } catch (MessagingException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
