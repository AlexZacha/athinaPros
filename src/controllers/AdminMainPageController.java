package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import athina.Account;
import athina.Athina;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.*;

public class AdminMainPageController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private TextField usernameLabel, lastnameLabel, nameLabel, emailLabel, nameField, lastnameField, emailField, examField;
    @FXML
    private AnchorPane arxikhSelidaPanel, userEnrollPanel;
    @FXML
    private ChoiceBox userRolle = new ChoiceBox();
    @FXML
    private Label nameLabelEgr, lastnameLabelEgr, emailLabelEgr, examLabelEgr;
    @FXML
    private Button egrBtn;
    private boolean first = true;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();


    @FXML
    private void setLabels(ActionEvent actionEvent) {
        setVis();
        arxikhSelidaPanel.setVisible(true);
        Admin admin = (Admin) Athina.user;
        usernameLabel.setText(admin.getUsername());
        lastnameLabel.setText(admin.getLastName());
        nameLabel.setText(admin.getFirstName());
        emailLabel.setText(admin.getEmail());
    }

    @FXML
    private void eisagwghXrhsth(ActionEvent actionEvent) {
        setVis();
        userEnrollPanel.setVisible(true);
        if (first){
            userRolle.getItems().add("Φοιτητής");
            userRolle.getItems().add("Καθηγητής");
            userRolle.getItems().add("Γραματεία");
        }
        userRolle.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                nameLabelEgr.setVisible(true); lastnameLabelEgr.setVisible(true); emailLabelEgr.setVisible(true);
                nameField.setVisible(true); lastnameField.setVisible(true); emailField.setVisible(true);
                examLabelEgr.setVisible(false); examField.setVisible(false);
                egrBtn.setVisible(true);
                if (userRolle.getValue().equals("Φοιτητής")){
                    examLabelEgr.setVisible(true); examField.setVisible(true);
                }
            }
        });
        first = false;
    }

    private void setVis(){
        arxikhSelidaPanel.setVisible(false);
        userEnrollPanel.setVisible(false);
    }

    @FXML
    private void addEgr(ActionEvent event){
        userEnrollPanel.setVisible(true);
        if (userRolle.getValue().equals("Φοιτητής")){
            Student temp = new Student(nameField.getText().substring(0,1) + lastnameField.getText(),
                    "123", nameField.getText(), lastnameField.getText(), emailField.getText(),Integer.parseInt(examField.getText()),
                            dtf.format(now));
                Account.insertStudent(temp);

        }else if(userRolle.getValue().equals("Καθηγητής")){
            Professor temp = new Professor(nameField.getText().substring(0,1) + lastnameField.getText(),
                    "123", nameField.getText(), lastnameField.getText(), emailField.getText());
            Account.insertProfessor(temp);
        }else{
            Admin temp = new Admin(nameField.getText().substring(0,1) + lastnameField.getText(),
                    "123", nameField.getText(), lastnameField.getText(), emailField.getText());
            Account.insertAdmin(temp);
        }


    }


    @FXML
    private void logoutButtonPressed(ActionEvent event)
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
