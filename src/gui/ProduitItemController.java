
package gui;


import static gui.ModifierCategorieProduitController.categorie;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import entities.*;
import services.*;

public class ProduitItemController implements Initializable {

    @FXML
    private Label cat;
    @FXML
    private Label nomlabel;
    @FXML
    private Label descriptionlabel;
    @FXML
    private ImageView imgitem;
    
    @FXML
    private Label Prix;
    @FXML
    private Label Quantite;
    private Produit produit;
    
    
    
   
   @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setData(Produit produit) {
        this.produit = produit;
        cat.setText(produit.getCategorie());
        nomlabel.setText(produit.getNom_prod());
        descriptionlabel.setText(produit.getDescription());
        Prix.setText(Integer.toString(produit.getPrix()));
        Quantite.setText(Integer.toString(produit.getNbr_prods()));
        Image image = new Image(getClass().getResourceAsStream("../images/"+produit.getImage()));
       imgitem.setImage(image);
     
    }

  
  @FXML
  
    private void delbtn(ActionEvent event) throws IOException {
        produit_service service = new produit_service();
        
        // Confirmation de la suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce produit ?");
        alert.showAndWait();
        
        service.delete(produit.getId());
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
        
    }
    
    
      @FXML
    private void btnclicked(ActionEvent event) throws IOException {
        ModifierProduitController.produit = produit;
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ModifierProduit.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
  
}
