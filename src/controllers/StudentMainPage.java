package controllers;

import athina.Account;
import athina.Athina;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.CourseRegistration;
import models.Student;
import models.User;

import java.io.IOException;

public class StudentMainPage {

    @FXML
    private   TextField usernameLabel, lastnameLabel, nameLabel, amLabel, examLabel, emailLabel, roleLabel;
    @FXML
    private ToggleGroup group;
    @FXML
    private RadioButton arxikhSelida, bathmologies, mathhmata, dhlwseis;
    @FXML
    private AnchorPane arxikhSelidaPanel, bathmologiesPanel, mathhmataPanel;
    @FXML
    private ListView bathmList = new ListView();
    @FXML
    private ListView mathhmataList = new ListView();
    private boolean firstTime = true;
    private boolean firstTimeTwo = true;

    @FXML
    public void setLabels(ActionEvent actionEvent) {
        setVis();
        arxikhSelidaPanel.setVisible(true);
        Student student = (Student) Athina.user;
        roleLabel.setText("Στοιχεία Φοιτητή");
        usernameLabel.setText(student.getUsername());
        lastnameLabel.setText(student.getLastName());
        nameLabel.setText(student.getFirstName());
        examLabel.setText(String.valueOf(student.getCurrentSemester()));
        emailLabel.setText(student.getEmail());
    }

    @FXML
    public void emfBathologias(ActionEvent event){
        setVis();
        bathmologiesPanel.setVisible(true);
        CourseRegistration[] courseRegistration;
        Student student = (Student) Athina.user;
        courseRegistration = student.getBathmologies(student.getUsername());
        for (int i =0; i<courseRegistration.length; i++){
            if (courseRegistration[i] != null && firstTime){
                    bathmList.getItems().add((courseRegistration[i].toString()));
            }
        }
        firstTime = false;
    }

    @FXML
    public void emfMathhmatwn(ActionEvent event){
        setVis();
        mathhmataPanel.setVisible(true);
        Student student = (Student) Athina.user;
        for (int i =0; i< Account.registrations.length; i++){
            if (Account.registrations[i] != null && Account.registrations[i].getStudent().getUsername().equals(student.getUsername()) && firstTimeTwo){
                mathhmataList.getItems().add(Account.registrations[i].getCourse());
            }
        }
        firstTimeTwo = false;
    }

    private void setVis(){
        arxikhSelidaPanel.setVisible(false);
        bathmologiesPanel.setVisible(false);
        mathhmataPanel.setVisible(false);
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
