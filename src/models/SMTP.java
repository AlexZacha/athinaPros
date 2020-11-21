package models;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SMTP {
    public static void sendEmail(String recepient) {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String emailUsername = "athinasmtp@gmail.com";
        String emailPass = "h0SV6Wu9Tr5QXrx";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailUsername, emailPass);
            }
        });
        Message message = prepareMessage(session, emailUsername, recepient);
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Message prepareMessage(Session session, String emailUsarname, String recipient){
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailUsarname));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Στοιχεία εισόσου για την εφαρμογή Athina.");
            message.setText("Hey from java YO YO");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}