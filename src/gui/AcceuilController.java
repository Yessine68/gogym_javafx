/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author don7a
 */
public class AcceuilController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToEvent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ibrahimhome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void GoToProduit(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Ademhome.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    private void goToHana(ActionEvent event) throws IOException {
         // Load the first FXML file
Parent root1 = FXMLLoader.load(getClass().getResource("/gui/Client/GoGymClient.fxml"));
Scene scene1 = new Scene(root1);
Stage stage1 = new Stage();
stage1.setScene(scene1);
stage1.show();

// Load the second FXML file
Parent root2 = FXMLLoader.load(getClass().getResource("/gui/Admin/GoGym.fxml"));
Scene scene2 = new Scene(root2);
Stage stage2 = new Stage();
stage2.setScene(scene2);
stage2.show();
        
    }
    
    
}
