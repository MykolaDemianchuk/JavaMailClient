package com.demianchuk;

import com.demianchuk.controller.SendEmailController;
import com.demianchuk.controller.SignInController;
import com.demianchuk.model.*;
import com.demianchuk.server.MailServer;
import com.demianchuk.view.*;

public class App {

    public static void main(String[] args) {
        new App().go();
    }

    private void go() {

        // change accordingly [Gmail, Yahoo]
        MailServer server = new xxxServer();

        // creating a client for particular server
        Client client = new Client(server);

        SignInView signInView = new SignInView();
        SendEmailView sendEmailView = new SendEmailView();

        new SignInController(client, signInView, sendEmailView);
        new SendEmailController(client, sendEmailView);
    }
}
