/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Admin;
import athina.Athina;
import athina.Account;
import models.Professor;
import models.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author FreeMike
 */
public class LoginPageController implements Initializable {
    
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label errorLabel;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    //ελεγχος αν τα πεδια ειναι κενα 
    public void loginButtonPressed(ActionEvent event)
    {
        String username=usernameField.getText();
        String password=passwordField.getText();
        
        if(username.isEmpty() || password.isEmpty())
        {
            errorLabel.setText("Τα πέδια είναι κενά");
            return;
        }
        login(username, password, event);  
    }
    
    private void login(String username,String password,ActionEvent event)
    {
        for(Professor p : Account.professors)
        {
            if(p!=null)
            {
                if(p.getUsername().equals(username) && p.getPassword().equals(password))
                {
                    Athina.user=p;
                    gotToMaster(event);
                    return;
                    
                }
            }
        }
        
        for(Student s : Account.students)
        {
            if(s!=null)
            {
                if(s.getUsername().equals(username) && s.getPassword().equals(password))
                {
                    Athina.user=s;
                    try{
                        Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/views/StudentMainPage.fxml")));
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
                    return;
                }
            }
        }
        
        for(Admin a : Account.admins)
        {
            if(a!=null)
            {
                if(a.getUsername().equals(username) && a.getPassword().equals(password))
                {
                    Athina.user=a;
                    gotToMaster(event);
                    return;
                }
            }
        }
        errorLabel.setText("Τα στοιχεία εισόδου δεν είναι σωστά");
    }
    
    
    //εδω ειναι η κεντρικη σελιδα 
    
    
    public void gotToMaster(ActionEvent event)
    {
        try{
            Scene scene=new Scene(FXMLLoader.load(getClass().getResource("/views/MasterPage.fxml")));
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
  
    
    
}
