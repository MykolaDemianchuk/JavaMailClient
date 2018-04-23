package com.demianchuk.model;

import com.demianchuk.servers.MailServer;

import javax.mail.*;
import javax.mail.internet.*;

public class MailClient {
    private Session session;
    private MailServer server;

    public MailClient(MailServer server) {
        this.server = server;
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
        Message email = new MimeMessage(session);
        email.setSubject(subject);
        email.setText(emailBody);
        try {
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addressList));
            new Thread(new SendEmailThread(email)).start();
        } catch (Exception e) {
            throw new Exception("Email delivery failure!");
        }
    }

    class SendEmailThread implements Runnable {
        private Message email;

        protected SendEmailThread(Message email) {
            this.email = email;
        }

        @Override
        public void run() {
            try {
                Transport.send(email);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}