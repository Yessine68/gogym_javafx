/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HanaM
 */
public class GoGymClientController implements Initializable {

    @FXML
    private Button sallebtn;
    @FXML
    private Button abnmtbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void navigateAbonnement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AbonnementClient.fxml"));
            Parent root = loader.load();
            
            AbonnementClientController controller = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @FXML
    private void navigateSalle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SalleMenu.fxml"));
            Parent root = loader.load();
            
            SalleMenuController controller = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            primaryStage.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}