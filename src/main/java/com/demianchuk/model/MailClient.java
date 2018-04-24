package com.demianchuk.model;

import com.demianchuk.servers.MailServer;
import javax.mail.*;

public class MailClient {

    private MailServer server;
    private Authenticator authenticator;
    private Session session;
    private Transport transport;

    public MailClient(MailServer server) {
        this.server = server;
    }

    public void setAuthenticator(final String username, final char[] password) {
        authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, new String(password));
            }
        };
    }

    public void createSession() {
        session = Session.getInstance(server.getProperties(), authenticator);
    }

    public Transport getTransport() {
        try {
            transport = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return transport;
    }

    public Session getSession() {
        return session;
    }
}