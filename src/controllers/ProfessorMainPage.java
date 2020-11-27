package controllers;

import athina.Account;
import athina.Athina;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Course;
import models.Professor;
import models.Student;
import models.User;

import java.io.IOException;

public class ProfessorMainPage {

    @FXML
    private AnchorPane arxikhSelidaPanel, mathhmataPanel;
    @FXML
    private TextField usernameLabel, lastnameLabel, nameLabel, emailLabel, amField;
    @FXML
    private ChoiceBox choiceBoxMath, choiceBoxHmer;
    @FXML
    private ListView studensList;
    @FXML
    private Button searchBtn;
    @FXML
    private TextArea statistics;
    @FXML
    private ToggleGroup group;


    private float sumBathmwn = 0;
    private int sumFoitPouEdwsan = 0;
    private int sumFoit = 0;
    private int toPerasan = 0;

    @FXML
    private void setLabels(ActionEvent actionEvent) {
        setVis();
        arxikhSelidaPanel.setVisible(true);
        Professor professor = (Professor) Athina.user;
        usernameLabel.setText(professor.getUsername());
        lastnameLabel.setText(professor.getLastName());
        nameLabel.setText(professor.getFirstName());
        emailLabel.setText(professor.getEmail());
    }

    @FXML
    private void getMathhmata(ActionEvent event) {
        setVis();
        mathhmataPanel.setVisible(true);
        choiceBoxMath.getItems().clear();
        choiceBoxHmer.getItems().clear();
        studensList.getItems().clear();
        statistics.clear();
        for (int i = 0; i< Account.courses.length; i++){
            if (Account.courses[i] != null && Athina.user.getUsername().equals(Account.courses[i].getProfessor().getUsername())){
                choiceBoxMath.getItems().add(Account.courses[i].getName());
            }
        }
        choiceBoxMath.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                choiceBoxHmer.getItems().clear();
                getHmerom((String) choiceBoxMath.valueProperty().getValue());
            }
        });
    }

    private void getHmerom(String courseName) {
        for (int i = 0; i < Account.registrations.length; i++) {
            if (Account.registrations[i] != null && Account.registrations[i].getCourse().getName().equals(courseName)) {
                if (!choiceBoxHmer.getItems().contains(Account.registrations[i].getRegistrationSemester())) {
                    choiceBoxHmer.getItems().add(Account.registrations[i].getRegistrationSemester());
                }
            }
        }
        choiceBoxHmer.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                studensList.getItems().clear();
                getStudens((String) choiceBoxMath.valueProperty().getValue(), (String) choiceBoxHmer.valueProperty().getValue());
            }
        });
    }

    private void getStudens(String course, String hmer){
        sumFoitPouEdwsan = 0;
        sumBathmwn = 0;
        sumFoit = 0;
        toPerasan = 0;
        statistics.clear();
        for (int i = 0; i < Account.registrations.length; i++){
            if (Account.registrations[i] != null && Account.registrations[i].getCourse().getName().equals(course) &&Account.registrations[i].getRegistrationSemester().equals(hmer)){
                studensList.getItems().add(Account.registrations[i].getStudent().getUsername() + " " + Account.registrations[i].getStudent().getFirstName() + " " +
                        Account.registrations[i].getStudent().getLastName() + " " + Account.registrations[i].toString2());
                        sumFoit++;
                        if (Account.registrations[i].getBathmos()!=-1 && Account.registrations[i].getKainBathmos() == -1){
                            sumFoitPouEdwsan++;
                            sumBathmwn+= Account.registrations[i].getBathmos();
                            if (Account.registrations[i].getBathmos() >=5) toPerasan++;
                        }else if (Account.registrations[i].getBathmos()!=-1 && Account.registrations[i].getKainBathmos() != -1){
                            sumFoitPouEdwsan++;
                            sumBathmwn+=Account.registrations[i].getKainBathmos();
                            if (Account.registrations[i].getKainBathmos() >=5) toPerasan++;
                        }
            }
        }statistics.setText("Μέσος όρος: \t" +  String.format("%.2f", sumBathmwn/sumFoitPouEdwsan) + "\n"
                            + "Σύνολο φοιτητών που το δήλωσαν: \t" + sumFoit + "\n"
                            + "Σύνολο φοιτητών που έγραψαν: \t" + sumFoitPouEdwsan + "\n"
                            + "Σύνολο φοιτητών που το πέρασαν: \t" + toPerasan);
    }

    @FXML
    private void searchStudent(ActionEvent event){
        for (int j = 0; j< Account.registrations.length; j++){
            if (Account.registrations[j] != null && Account.registrations[j].getStudent().getUsername().equals(amField.getText())){
                studensList.getItems().clear();
                studensList.getItems().add(Account.registrations[j]);
                statistics.clear();
            }
        }
    }

    private void setVis(){
        arxikhSelidaPanel.setVisible(false);
        mathhmataPanel.setVisible(false);
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
