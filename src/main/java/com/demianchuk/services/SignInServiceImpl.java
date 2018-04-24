package com.demianchuk.services;

import com.demianchuk.model.MailClient;
import javax.mail.MessagingException;

public class SignInServiceImpl implements SignInService {

    @Override
    public void signIn(MailClient client, String username, char[] password) throws Exception{
        client.setAuthenticator(username, password);
        client.createSession();
        try {
            client.getTransport().connect();
        } catch (MessagingException e) {
            throw new Exception("Invalid credentials!");
        }
    }
}
