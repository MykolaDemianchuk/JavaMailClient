package com.demianchuk.model;

import com.demianchuk.servers.MailServer;
import com.demianchuk.services.SendEmailService;
import com.demianchuk.services.SendEmailServiceImpl;

import javax.mail.*;
import javax.mail.internet.*;

public class MailClient {
    private Session session;
    private MailServer server;
    private SendEmailService sendEmailService;

    public MailClient(MailServer server) {
        this.server = server;
        sendEmailService = new SendEmailServiceImpl();
    }

    public void signIn(final String username, final char[] password) throws Exception {
        //TODO handle bad credentials exception
        session = Session.getInstance(server.getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, new String(password));
            }
        });
        session.getTransport().connect();
    }

    public void sendEmail(String addressList, String subject, String emailBody) throws Exception {
        sendEmailService.sendEmail(session, addressList, subject, emailBody);
    }
}