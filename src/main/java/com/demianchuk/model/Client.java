package com.demianchuk.model;

import com.demianchuk.server.MailServer;
import com.demianchuk.service.SessionService;
import com.demianchuk.service.SignInService;

import javax.mail.*;

public class Client {

    private MailServer server;
    private Session session;

    public Client(MailServer server) {
        this.server = server;
    }

    public Session getSession() {
        return session;
    }

    public void signIn(String username, char[] password) throws Exception {
        session = SessionService.configureSession(server.getProperties(), username, password);
        SignInService.signIn(session);
    }
}
