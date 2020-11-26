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
    private TextField usernameLabel, lastnameLabel, nameLabel, amLabel, examLabel, emailLabel, roleLabel, mesosOrosText,dmTextField, perasmMathField;
    @FXML
    private ToggleGroup group;
    @FXML
    private RadioButton arxikhSelida, bathmologies, mathhmata, dhlwseis;
    @FXML
    private AnchorPane arxikhSelidaPanel, bathmologiesPanel, mathhmataPanel, dhlwseisPanel;
    @FXML
    private ListView bathmList = new ListView();
    @FXML
    private ListView mathhmataList = new ListView();
    private boolean firstTime = true;
    private boolean firstTimeTwo = true;

    public StudentMainPage() {
    }

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
        if (firstTime){
            CourseRegistration[] courseRegistration;
            Student student = (Student) Athina.user;
            courseRegistration = student.getBathmologies(student.getUsername());
            float sum = 0;
            int count = 0;
            int dm = 0;
            for (int i =0; i<courseRegistration.length; i++){
                if (courseRegistration[i] != null){
                        bathmList.getItems().add((courseRegistration[i].toString()));
                        if (courseRegistration[i].getKainBathmos() != -1 && courseRegistration[i].getKainBathmos() >= 5){
                            sum+= courseRegistration[i].getKainBathmos();
                            dm+= courseRegistration[i].getCourse().getCredits();
                            count++;
                        }
                        else if (courseRegistration[i].getBathmos() != -1 && courseRegistration[i].getBathmos() >= 5){
                            sum+= courseRegistration[i].getBathmos();
                            dm+= courseRegistration[i].getCourse().getCredits();
                            count++;
                        }

                }

        }
            String mesosOros = String.format("%.2f", sum/count);
            mesosOrosText.setText(mesosOros);
            dmTextField.setText(String.valueOf(dm));
            perasmMathField.setText(String.valueOf(count));
        }
        firstTime = false;
    }

    @FXML
    public void emfMathhmatwn(ActionEvent event){
        setVis();
        mathhmataPanel.setVisible(true);
        if (firstTimeTwo){
            for (int i =0; i< Account.courses.length; i++){
                if (Account.courses[i] != null )
                    mathhmataList.getItems().add(Account.courses[i]);
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
