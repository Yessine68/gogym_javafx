
package gui;

import entities.*;
import services.*;
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


public class CategorieProdItemController implements Initializable {

    @FXML
    private Label idlabel;
    @FXML
    private Label nomlabel;
    private Listener1 listener1;

 private categorie categorie;


@FXML
    private void editpressed(MouseEvent mouseEvent){
        listener1.onClickListener(categorie);
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void setData(categorie categorie, Listener1 listener1){
        this.categorie = categorie;
        this.listener1 = listener1;  
        idlabel.setText(String.valueOf(categorie.getId()));
        nomlabel.setText(categorie.getNom());
    }
    
     @FXML
    private void btnclicked(ActionEvent event) throws IOException {
        ModifierCategorieProduitController.categorie = categorie;
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ModifierCategorieProduit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    

  @FXML
  
    private void delbtn(ActionEvent event) throws IOException {
        categorie_Service service = new categorie_Service();
        
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette categorie ?");
        alert.showAndWait();
        service.delete(categorie.getId());
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionCatProd.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
        
    }
    
    
    
}
