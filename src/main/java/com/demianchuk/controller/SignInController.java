package com.demianchuk.controller;

import com.demianchuk.model.Client;
import com.demianchuk.view.SendEmailView;
import com.demianchuk.view.DialogBox;
import com.demianchuk.view.SignInView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInController {

    private Client client;
    private SignInView signInView;
    private SendEmailView sendEmailView;

    public SignInController(Client client, SignInView signInView, SendEmailView sendEmailView) {
        this.client = client;
        this.signInView = signInView;
        this.sendEmailView = sendEmailView;
        this.signInView.addSignInButtonListener(new SignInButtonListener());
    }

    class SignInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                client.signIn(signInView.getUsername(), signInView.getPassword());
                sendEmailView.displayWindow();
                sendEmailView.setSender(signInView.getUsername());
                signInView.hideWindow();
            } catch (Exception ex) {
                DialogBox.displayErrorMessage(signInView, ex.getMessage());
            }
        }
    }
}
