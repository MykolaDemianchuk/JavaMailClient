package com.demianchuk.services;

import com.demianchuk.model.MailClient;

public interface SendEmailService {
    void sendEmail(MailClient client, String addressList,
                   String subject, String emailBody) throws Exception;
}
