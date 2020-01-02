package com.pkran.mailservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
public class EmailService {

    @Value("${email.provider}")
    public String PROVIDER;

    @Value("${email.user}")
    public String HOST_USER;

    @Value("${email.address}")
    public String HOST_ADDRESS;

    @Value("${email.password}")
    public String HOST_PASSWORD;

    @Value("${email.protocol}")
    public String PROTOCOL;

    @Value("${email.contenttype}")
    public String CONTENT_TYPE;

    private static final Session SESSION = Session.getInstance(new Properties());


    /**
     * generates a MimeMessage from a strings and sends it to recipients based on config
     */
    public void createMessageAndSent(EmailDTO emailDTO) {
        try {
            MimeMessage message = new MimeMessage(SESSION);
            Address[] recipients = emailDTO.getRecipients().stream()
                    .map(s -> {
                        try {
                            return new InternetAddress(s);
                        } catch (AddressException e) {
                            throw new RuntimeException(e.getMessage());
                        }
                    })
                    .toArray(Address[]::new);

            message.setFrom(new InternetAddress(HOST_ADDRESS, emailDTO.getSender()));
            message.addRecipients(Message.RecipientType.TO, recipients);
            message.setSubject(emailDTO.getTitle());
            message.setContent(emailDTO.getBody(), CONTENT_TYPE);
            Transport t = SESSION.getTransport(PROTOCOL);
            t.connect(PROVIDER, HOST_USER, HOST_PASSWORD);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("MessagingException:" + e);
        }
    }

}
