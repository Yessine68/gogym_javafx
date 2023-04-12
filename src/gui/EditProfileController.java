/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;


/**
 * FXML Controller class
 *
 * @author don7a
 */
public class EditProfileController implements Initializable {
    
    private User user;
    @FXML
    private TextField usernamepTF;
    @FXML
    private TextField nompTF;
    @FXML
    private TextField prenompTF;
    @FXML
    private TextField emailpTF;
      
    public void setUser(User user) {
        this.user = user;
        usernamepTF.setText(user.getUsername());
        nompTF.setText(user.getNom());
        prenompTF.setText(user.getPrenom());
        emailpTF.setText(user.getEmail());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update(ActionEvent event) {
         try {
        UserService userService = new UserService();
        String newUsername = usernamepTF.getText();
        String newNom = nompTF.getText();
        String newPrenom = prenompTF.getText();
        String newEmail = emailpTF.getText();
        
        user.setUsername(newUsername);
        user.setNom(newNom);
        user.setPrenom(newPrenom);
        user.setEmail(newEmail);
         
        userService.modifier(user);
        System.out.println("User " + user.getUsername() + " updated in the database.");
        
        // Load the Profile.fxml view with the updated user credentials
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        Parent root = loader.load();
        ProfileController profileController = loader.getController();
        profileController.setUser(user);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
    
}
