package com.demianchuk.controller;

import com.demianchuk.model.Client;
import com.demianchuk.service.SendEmailService;
import com.demianchuk.service.ValidatorService;
import com.demianchuk.view.SendEmailView;
import com.demianchuk.view.DialogBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendEmailController {

    private Client client;
    private SendEmailView view;

    public SendEmailController(Client client, SendEmailView view) {
        this.client = client;
        this.view = view;
        this.view.addSendButtonListener(new SendButtonListener());

    }

    class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ValidatorService.validate(view.getRecipients());
                SendEmailService.sendEmail(client, view.getRecipients(), view.getSubject(), view.getEmailBody());
                DialogBox.displayInfoMessage(view, "Email will be delivered shortly");
            } catch (Exception ex) {
                DialogBox.displayErrorMessage(view, ex.getMessage());
            }
        }
    }
}
