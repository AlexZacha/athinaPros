package controllers;

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
    private AnchorPane arxikhSelidaPanel, bathmologiesPanel;
    @FXML
    private ListView bathmList = new ListView();
    private boolean firstTime = true;

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
        CourseRegistration courseRegistration[];
        Student student = (Student) Athina.user;
        courseRegistration = student.getBathmologies(student.getUsername());
        for (int i =0; i<courseRegistration.length; i++){
            if (courseRegistration[i] != null && firstTime && courseRegistration[i].getBathmos() != -1){
                bathmList.getItems().add((courseRegistration[i].toString()));

            }
        }
        firstTime = false;
    }

    private void setVis(){
        arxikhSelidaPanel.setVisible(false);
        bathmologiesPanel.setVisible(false);
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
