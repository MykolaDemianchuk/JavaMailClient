package com.demianchuk.service;

import com.demianchuk.model.Client;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailService {

    private SendEmailService() {
    }

    public static void sendEmail(Client client, String addressList, String subject, String emailBody) throws Exception {
        if (addressList.isEmpty())
            throw new Exception("Add at least 1 recipient!");
        if (subject.isEmpty())
            throw new Exception("Your message has no subject!");
        if (emailBody.isEmpty())
            throw new Exception("Your email body has no content!");

        Message email = new MimeMessage(client.getSession());
        email.setSubject(subject);
        email.setText(emailBody);

        try {
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addressList));
            Transport.send(email);
        } catch (Exception e) {
            throw new Exception("Email delivery failure!");
        }
    }
}
