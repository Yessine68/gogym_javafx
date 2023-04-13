/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.CategorieEvenement;
import Services.CategorieEvenementService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CategorieItemController implements Initializable {

    @FXML
    private Label idlabel;
    @FXML
    private Label nomlabel;
    private Listener1 listener1;

 private CategorieEvenement categorie;


@FXML
    private void editpressed(MouseEvent mouseEvent){
        listener1.onClickListener(categorie);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(CategorieEvenement categorie, Listener1 listener1){
        this.categorie = categorie;
        this.listener1 = listener1;  
        idlabel.setText(String.valueOf(categorie.getId()));
        nomlabel.setText(categorie.getNom_cat_e());
    }
    
     @FXML
    private void btnclicked(ActionEvent event) throws IOException {
        ModifierCategorieEvenementController.categorie = categorie;
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ModifierCategorieEvenement.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    

  @FXML
  
    private void delbtn(ActionEvent event) throws IOException {
        CategorieEvenementService service = new CategorieEvenementService();
        
        // Confirmation de la suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette categorie ?");
        alert.showAndWait();
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Gestioncatevent.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
        service.delete(categorie);
    }
    
    
    
}
