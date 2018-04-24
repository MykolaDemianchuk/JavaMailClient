package com.demianchuk;

import com.demianchuk.model.*;
import com.demianchuk.views.*;
import com.demianchuk.controller.MailClientController;
import com.demianchuk.servers.GmailServer;

public class App {

    public static void main(String[] args) {
        new App().go();
    }

    private void go() {
        MailClient gmailClient = new MailClient(new GmailServer());
        new MailClientController(gmailClient, new SignInView(), new ClientView());
    }
}
