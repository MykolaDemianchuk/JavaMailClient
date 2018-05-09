package com.demianchuk.server;

import java.util.Properties;

public class MailServer {

    private Properties properties;

    protected MailServer(String host) {
        properties = new Properties();
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
    }

    public Properties getProperties() {
        return properties;
    }
}
