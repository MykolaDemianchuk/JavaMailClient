package com.demianchuk.controller;

import com.demianchuk.views.*;
import com.demianchuk.services.*;
import com.demianchuk.model.MailClient;
import com.demianchuk.util.ValidatorUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MailClientController {

    private MailClient client;
    private SignInView signInView;
    private ClientView clientView;

    public MailClientController(MailClient client, SignInView signInView, ClientView clientView) {
        this.client = client;
        this.signInView = signInView;
        this.clientView = clientView;
        this.signInView.addSignInButtonListener(new SignInButtonListener());
        this.clientView.addSendButtonListener(new SendButtonListener());
    }

    class SignInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new SignInServiceImpl().signIn(client, signInView.getUsername(), signInView.getPassword());
                signInView.hideWindow();
                clientView.displayWindow();
                clientView.setSender(signInView.getUsername());
            } catch (Exception ex) {
                DialogBox.displayErrorMessage(signInView, ex.getMessage());
            }
        }
    }

    class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ValidatorUtil.validate(clientView.getRecipient());
                new SendEmailServiceImpl().sendEmail(client, clientView.getRecipient(), clientView.getSubject(), clientView.getEmailBody());
                DialogBox.displayInfoMessage(clientView, "Email will be delivered shortly");
            } catch (Exception ex) {
                DialogBox.displayErrorMessage(clientView, ex.getMessage());
            }
        }
    }
}
