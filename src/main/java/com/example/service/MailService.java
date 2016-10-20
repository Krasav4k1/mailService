package com.example.service;

import com.example.config.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Mail Service fot mail controller
 * in this service all functional for send email to user
 *
 * @author Andrii Blyzniuk
 */

@Service
public class MailService {

    @Autowired
    MailProperties mailProperties;

    //This method is executed to send letters to users by mail
    public void sendMessageToEmail(String receiverEmail, String subject, String text) throws MessagingException {


        final String username = mailProperties.getUserName();
        final String password = mailProperties.getPassword();

        Properties props = new Properties();
        props.put("mail.transport.protocol", mailProperties.getProtocol());
        props.put("mail.smtp.auth", mailProperties.getAuth());
        props.put("mail.smtp.starttls.enable", mailProperties.getStarttlsEnable());
        props.put("mail.smtps.ssl.checkserveridentity", mailProperties.getCheckServerIdentity());
        props.put("mail.smtps.ssl.trust", mailProperties.getTrust());
        props.put("mail.smtp.starttls.required", mailProperties.getStarttlsRequired());
        props.put("mail.smtp.host", mailProperties.getHost());
        props.put("mail.smtp.port", mailProperties.getPort());
        props.put("mail.debug", mailProperties.getDebug());

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
        message.setSubject(subject);
        message.setContent(text, "text/html");

        Transport.send(message);


    }

}
