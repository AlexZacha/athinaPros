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
import javafx.stage.Stage;
import models.Course;
import models.CourseRegistration;
import models.Student;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StudentMainPage {

    @FXML
    private TextField usernameLabel, lastnameLabel, nameLabel, amLabel, examLabel, emailLabel, roleLabel, mesosOrosText,dmTextField, perasmMathField, sunoloDM, sunoloMath,errorField;
    @FXML
    private AnchorPane arxikhSelidaPanel, bathmologiesPanel, mathhmataPanel, dhlwseisPanel;
    @FXML
    private ListView bathmList = new ListView();
    @FXML
    private ListView mathhmataList = new ListView();
    @FXML
    private ListView olaTaMath = new ListView();
    @FXML
    private ListView epilegmenaMath = new ListView();
    @FXML
    private Button addBtn, removeBtn;
    @FXML
    private Label confirmText;

    private int dm = 0;
    private int arithmosMath = 0;
    private boolean firstTime = true;
    private boolean firstTimeTwo = true;
    private boolean firstTimeTree = true;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

    @FXML
    private void setLabels(ActionEvent actionEvent) {
        setVis();
        arxikhSelidaPanel.setVisible(true);
        Student student = (Student) Athina.user;
        usernameLabel.setText(student.getUsername());
        lastnameLabel.setText(student.getLastName());
        nameLabel.setText(student.getFirstName());
        examLabel.setText(String.valueOf(student.getCurrentSemester()));
        emailLabel.setText(student.getEmail());
    }

    @FXML
    private void emfBathologias(ActionEvent event){
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
    private void emfMathhmatwn(ActionEvent event){
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

    @FXML
    private void dhlwseisMath(ActionEvent event){
        setVis();
        dhlwseisPanel.setVisible(true);
        if (firstTimeTree){
            for (int i = 0; i < Account.courses.length; i++){
                if (Account.courses[i] != null && !toExeiDhlwsei(i)){
                    olaTaMath.getItems().add(Account.courses[i]);
                }
            }
        }
        firstTimeTree = false;
    }

    private boolean toExeiDhlwsei(int y){
        Student student = (Student) Athina.user;
        for (int i = 0; i< Account.registrations.length; i++){
            if (Account.registrations[i] != null && Account.registrations[i].getStudent() == student && Account.registrations[i].getCourse() == Account.courses[y]) {
                return true;
            }
        }
        return false;
    }


    @FXML
    private void addMathima(ActionEvent event){
        Course course = (Course) olaTaMath.getSelectionModel().getSelectedItem();
        if (olaTaMath.getSelectionModel().getSelectedItem() != null && (course.getCredits() + dm) <= 42){
            epilegmenaMath.getItems().add(olaTaMath.getSelectionModel().getSelectedItem());
            dm+=course.getCredits();
            arithmosMath++;
            olaTaMath.getItems().remove(olaTaMath.getSelectionModel().getSelectedItem());
            sunoloDM.setText(String.valueOf(dm));
            sunoloMath.setText(String.valueOf(arithmosMath));
        }
    }

    @FXML
    private void removeMathima(ActionEvent event){
        if (epilegmenaMath.getSelectionModel().getSelectedItem() != null){
            olaTaMath.getItems().add(epilegmenaMath.getSelectionModel().getSelectedItem());
            Course course = (Course) epilegmenaMath.getSelectionModel().getSelectedItem();
            dm-=course.getCredits();
            arithmosMath--;
            epilegmenaMath.getItems().remove(epilegmenaMath.getSelectionModel().getSelectedItem());
            sunoloDM.setText(String.valueOf(dm));
            sunoloMath.setText(String.valueOf(arithmosMath));
        }
    }

    @FXML
    private void dhlwshMathhmatwn(){
        if (!epilegmenaMath.getItems().isEmpty()){
            for (int i = 0; i < epilegmenaMath.getItems().size(); i++){
                for (int j = 0; j < Account.registrations.length; j++){
                    if (Account.registrations[j] != null){
                        Course course = (Course)  epilegmenaMath.getItems().get(i);
                        Account.registrations[j] = new CourseRegistration((Student) Athina.user, course, "2020-2021 XEIM",formatter.format(Calendar.getInstance().getTime()));
                        break;
                    }
                }
            }
        }
        confirmText.setVisible(true);
        addBtn.setDisable(true);
        removeBtn.setDisable(true);
    }

    private void setVis(){
        arxikhSelidaPanel.setVisible(false);
        bathmologiesPanel.setVisible(false);
        mathhmataPanel.setVisible(false);
        dhlwseisPanel.setVisible(false);
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
