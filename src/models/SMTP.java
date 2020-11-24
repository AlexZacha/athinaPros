package models;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SMTP {
    public static void sendEmail(String name, String lastname, String username, String pass) {
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
        Message message = prepareMessage(session, emailUsername,name, lastname, username, pass);
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Message prepareMessage(Session session, String emailUsarname, String name, String lastname, String username, String pass){
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailUsarname));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress());
            message.setSubject("Στοιχεία εισόσου για την εφαρμογή Athina.");
            message.setText("Παρακάτω δίνονται οι κωδικοί για την πρόσβαση στην εφαρμογή Athina για τον χρήστη " + name + " " + lastname + "\n" + "username: " +
                    username + "\npassword: " + pass);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}