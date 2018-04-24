package com.demianchuk.model;

import javax.mail.*;
import org.apache.log4j.Logger;
import com.demianchuk.servers.MailServer;

public class MailClient {
    private static final Logger LOGGER = Logger.getLogger(MailClient.class);

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
            LOGGER.warn(e.getMessage(), e);
        }
        return transport;
    }

    public Session getSession() {
        return session;
    }
}