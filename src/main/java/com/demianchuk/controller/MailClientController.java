package com.demianchuk.controller;

import com.demianchuk.model.MailClient;
import com.demianchuk.util.ValidatorUtil;
import com.demianchuk.views.ClientView;
import com.demianchuk.views.DialogBox;
import com.demianchuk.views.SignInView;

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
                client.signIn(signInView.getUsername(), signInView.getPassword());
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
                if (clientView.getRecipient().isEmpty())
                    throw new Exception("Add at least 1 recipient!");
                if(clientView.getSubject().isEmpty())
                    throw new Exception("Your message has no subject!");
                if(clientView.getEmailBody().isEmpty())
                    throw new Exception("Your email body has no content!");
                ValidatorUtil.validate(clientView.getRecipient());
                client.sendEmail(clientView.getRecipient(), clientView.getSubject(), clientView.getEmailBody());
                DialogBox.displayInfoMessage(clientView, "Email will be delivered shortly");
            } catch (Exception ex) {
                DialogBox.displayErrorMessage(clientView, ex.getMessage());
            }
        }
    }
}
