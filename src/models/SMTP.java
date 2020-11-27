package models;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SMTP {
    public static void sendEmail(String name, String lastname, String username, String pass, String recepient) {
        if (isValidEmailAddress(recepient)) {
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
            Message message = prepareMessage(session, emailUsername, name, lastname, username, pass, recepient);
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
    private static Message prepareMessage(Session session, String emailUsarname, String name, String lastname, String username, String pass, String recipient){
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailUsarname));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Στοιχεία εισόσου για την εφαρμογή Athina.");
            message.setText("Παρακάτω δίνονται οι κωδικοί για την πρόσβαση στην εφαρμογή Athina για τον χρήστη " + name + " " + lastname + "\n\n" + "username: " +
                    username + "\npassword: " + pass);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void test(ActionEvent event){
        try{
            Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/views/AdminMainPage.fxml")));
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setResizable(false);
            window.setTitle("Athina");
            window.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}