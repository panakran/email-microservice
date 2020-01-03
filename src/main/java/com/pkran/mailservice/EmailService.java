package com.pkran.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Value("${email.address}")
    public String HOST_ADDRESS;

    @Value("${email.contenttype}")
    public String CONTENT_TYPE;

    @Autowired
    public JavaMailSender emailSender;

    /**
     * generates a MimeMessage from a strings and sends it to recipients based on config
     */
    public void createMessageAndSent(EmailDTO emailDTO) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            Address[] recipients = emailDTO
                    .getRecipients()
                    .stream()
                    .map(EmailService::stringToInternetAddress)
                    .toArray(Address[]::new);

            message.setFrom(new InternetAddress(HOST_ADDRESS, emailDTO.getSender()));
            message.addRecipients(Message.RecipientType.TO, recipients);
            message.setSubject(emailDTO.getTitle());
            message.setContent(emailDTO.getBody(), CONTENT_TYPE);
            emailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println("MessagingException:" + e);
        }
    }

    private static InternetAddress stringToInternetAddress(String s) {
        try {
            return new InternetAddress(s);
        } catch (AddressException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
