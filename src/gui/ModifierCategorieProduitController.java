
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ModifierCategorieProduitController implements Initializable {

    @FXML
    private TextField editNom;
    static categorie categorie;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editNom.setText(categorie.getNom());  
    }    
    @FXML
    
    private void btnedit(ActionEvent event) throws IOException {
        String nom = editNom.getText();

        categorie_Service categorieService = new categorie_Service();
        categorie updatedCategorie = new categorie(categorie.getId(), nom);

        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText("Champ vide");
            alert.setContentText("Le nom de la catégorie ne peut pas être vide.");
            alert.showAndWait();
            return;
        }
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment modifier cette catégorie ?");
        alert.setContentText("Nom de la catégorie : " + nom);
        alert.showAndWait();

        categorieService.update(updatedCategorie);

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionCatProd.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
