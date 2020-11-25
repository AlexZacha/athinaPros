package controllers;

import athina.Athina;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Student;
import javafx.scene.control.TextField;
import models.User;

import java.io.IOException;

public class StudentMainPage {

    @FXML
    public  TextField usernameLabel, lastnameLabel, nameLabel, amLabel, examLabel, emailLabel, roleLabel;
    @FXML
    private Button arxikhSelida;
    @FXML
    private AnchorPane arxikhSelidaPanel;

    @FXML
    public void setLabels(ActionEvent actionEvent) {
        arxikhSelidaPanel.setVisible(true);
        Student student = (Student) Athina.user;
        roleLabel.setText("Στοιχεία Φοιτητή");
        usernameLabel.setText(student.getUsername());
        lastnameLabel.setText(student.getLastName());
        nameLabel.setText(student.getFirstName());
        examLabel.setText(String.valueOf(student.getCurrentSemester()));
        emailLabel.setText(student.getEmail());
    }

    public void logoutButtonPressed(ActionEvent event)
    {
        try{
            Athina.user=null;
            Scene loginScene=new Scene(FXMLLoader.load(getClass().getResource("/views/LoginPage.fxml")));
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.setResizable(false);
            window.setTitle("Athina-Login");
            window.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
