
package gui;

import entities.*;
import services.*;
import static gui.ModifierProduitController.produit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ModifierProduitController implements Initializable {

    @FXML
    private TextField Nom;
    @FXML
    private TextField Description;
    @FXML
    private TextField Quantite;
    @FXML
    private TextField Prix;
  
    @FXML
    private ComboBox<String> categorieCB;
    
    private List<categorie> categories = new ArrayList<>();
    public static Produit produit;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
   categorie_Service service = new categorie_Service();
    
    categories = service.readAll();
        
    for (int i = 0; i < categories.size(); i++){
     categorieCB.getItems().add(categories.get(i).getNom()); 
    }
        
        Nom.setText(produit.getNom_prod());
        Description.setText(produit.getCategorie());
        Quantite.setText(String.valueOf(produit.getNbr_prods()));
        Prix.setText(String.valueOf(produit.getPrix()));
        categorieCB.setValue(produit.getCategorie());
     
    }
    
    @FXML
     void modifierProduit(ActionEvent event) throws IOException {
        
        String nom_prod =  Nom.getText();
        String description = Description.getText();
        int nbr_prods = Integer.parseInt(Quantite.getText());
        int prix = Integer.parseInt( Prix.getText());
        String categorie = categorieCB.getValue();
        
        
        if (Nom.getText().isEmpty() ||Description.getText().isEmpty()  || Quantite.getText().isEmpty() || Prix.getText().isEmpty() || categorieCB.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
    } else {
        try {
            Produit p = new Produit();
            p.setNom_prod(Nom.getText());
            p.setDescription(Description.getText());
            p.setNbr_prods(Integer.parseInt(Quantite.getText()));
            p.setPrix(Integer.parseInt( Prix.getText()));
            p.setCAategorie(categorieCB.getValue());
            p.setId(produit.getId());
            p.setImage(produit.getImage());
            produit_service e = new produit_service();
            e.update(p);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Produit MOdifier avec succès");
            alert.showAndWait();

            
            Nom.clear();
            Description.clear();
            Quantite.clear();
            Prix.clear();
            categorieCB.getSelectionModel().clearSelection();

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la Modification du Produit");
            alert.showAndWait();
            ex.printStackTrace();

       
       
       
       
        }
    
    }
    
   Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
            Scene tabbleViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tabbleViewScene);
            window.show();
    }
    
    
    
    
    
}
