/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author don7a
 */
public class SignUpController implements Initializable {
    UserService us = new UserService();
    
    @FXML
    private TextField prenomTF;
    @FXML
    private TextField nomTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private TextField emailTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
private void SignUp(ActionEvent event) throws SQLException, IOException {
    String username = usernameTF.getText();
    String password = passwordTF.getText();
    String nom = nomTF.getText();
    String prenom = prenomTF.getText();
    String email = emailTF.getText();

       User user = new User(username, password, email, nom, prenom, "enabled", null,new String[] {"ROLE_USER"});


    us.ajouter(user);

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
    Parent parent = loader.load();
    LoginController controller = loader.getController();
    controller.setUser(user);
    usernameTF.getScene().setRoot(parent);
}
    
    
       @FXML
    private void gotoLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
//            controller.setUsername(usernameTF.getText());
            usernameTF.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    



}

 
    

