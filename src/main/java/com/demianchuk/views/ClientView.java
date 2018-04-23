package com.demianchuk.views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private JPanel thePanel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel subjectLabel;
    private JTextField fromField;
    private JTextField subjectField;
    private JTextField toField;
    private JScrollPane theScrollPane;
    private JTextArea emailArea;
    private JButton sendButton;

    public ClientView() {
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        thePanel = new JPanel();
        fromLabel = new JLabel("From");
        toLabel = new JLabel("To");
        subjectLabel = new JLabel("Subject");
        fromField = new JTextField();
        fromField.setEditable(false);
        toField = new JTextField();
        subjectField = new JTextField();
        emailArea = new JTextArea(15,50);
        theScrollPane = new JScrollPane(emailArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sendButton = new JButton("Send");

        GroupLayout layout = new GroupLayout(thePanel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(fromLabel)
                                .addComponent(toLabel)
                                .addComponent(subjectLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(fromField)
                                .addComponent(toField)
                                .addComponent(subjectField)))
                .addComponent(theScrollPane)
                .addComponent(sendButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fromLabel)
                        .addComponent(fromField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(toLabel)
                        .addComponent(toField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(subjectLabel)
                        .addComponent(subjectField))
                .addComponent(theScrollPane)
                .addComponent(sendButton)
        );

        thePanel.setLayout(layout);
        this.add(thePanel);
        this.pack();
    }

    public void addSendButtonListener(ActionListener listener) {
        sendButton.addActionListener(listener);
    }

    public void displayWindow() {
        setVisible(true);
    }

    public void setSender(String sender) {
        fromField.setText(sender);
    }

    public String getRecipient() {
        return toField.getText().trim();
    }

    public String getEmailBody() {
        return emailArea.getText();
    }

    public String getSubject() {
        return subjectField.getText();
    }
}
