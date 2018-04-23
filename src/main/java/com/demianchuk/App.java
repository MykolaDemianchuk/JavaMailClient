package com.demianchuk;

import com.demianchuk.controller.MailClientController;
import com.demianchuk.model.*;
import com.demianchuk.servers.GmailServer;
import com.demianchuk.views.*;

public class App {

    public static void main(String[] args) {
        new App().go();
    }

    private void go() {
        new MailClientController(new MailClient(new GmailServer()), new SignInView(), new ClientView());
    }
}
