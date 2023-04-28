
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
import javafx.stage.Stage;
import entities.*;
import services.*;

public class AdemhomeController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }   
    
    
   @FXML
  private void GestionProdtFront(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Prodfront.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    } 
  
    @FXML
    private void GestionProd(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    } 
  
  
  
     @FXML
  private void Gestioncategorie(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("GestionCatProd.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    } 


    
    
}
