package com.demianchuk.controller;

import com.demianchuk.model.MailClient;
import com.demianchuk.services.SendEmailServiceImpl;
import com.demianchuk.services.SignInService;
import com.demianchuk.services.SignInServiceImpl;
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
