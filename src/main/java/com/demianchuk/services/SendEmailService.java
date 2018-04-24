package com.demianchuk.services;

import javax.mail.Session;

public interface SendEmailService {
    void sendEmail(Session session, String addressList,
                          String subject, String emailBody) throws Exception;
}
