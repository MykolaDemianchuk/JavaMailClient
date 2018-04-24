package com.demianchuk.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailServiceImpl implements SendEmailService {

    @Override
    public void sendEmail(Session session, String addressList,
                          String subject, String emailBody) throws Exception {

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
