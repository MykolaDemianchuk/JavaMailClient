package com.demianchuk.services;

import com.demianchuk.model.MailClient;

public interface SignInService {
    void signIn(MailClient client, String username, char[] password) throws Exception;
}
