/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import models.Admin;
import athina.Athina;
import models.Professor;
import models.Student;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FreeMike
 */
public class MasterPageController implements Initializable {
        
    @FXML
    private Button logoutbutton;
    
    @FXML
    private Button statistikaButton;
            
    @FXML
    private Button diorthoVathButton;
    
    @FXML
    private Button finalizeGradeButton;
    
    @FXML
    private Button viewCourseGradesButton;
            
    @FXML
    private Button adduserButton;   
    
    @FXML   
    private Label usernameLabel;
    
    @FXML
    private AnchorPane pane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        if(Athina.user instanceof Student)
        {
            pane.getChildren().remove(adduserButton);
             pane.getChildren().remove(statistikaButton);
              pane.getChildren().remove(diorthoVathButton);
               pane.getChildren().remove(finalizeGradeButton);
                pane.getChildren().remove(adduserButton);
                usernameLabel.setText(Athina.user.getUsername());
                
        }  
        
            else if(Athina.user instanceof Professor)
        {
             pane.getChildren().remove(adduserButton);
             pane.getChildren().remove(finalizeGradeButton);
             pane.getChildren().remove(viewCourseGradesButton);
             usernameLabel.setText(Athina.user.getUsername());
        }
            else if (Athina.user instanceof Admin) {
                pane.getChildren().remove(statistikaButton);
                pane.getChildren().remove(viewCourseGradesButton);
                pane.getChildren().remove(diorthoVathButton);
                 usernameLabel.setText("Διαχειριστής");
            
        }
            else{
                pane.getChildren().remove(statistikaButton);
                pane.getChildren().remove(diorthoVathButton);
                pane.getChildren().remove(finalizeGradeButton);
                pane.getChildren().remove(viewCourseGradesButton);
                pane.getChildren().remove(adduserButton);
            }
        
        
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
