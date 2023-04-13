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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifierCategorieEvenementController implements Initializable {

    @FXML
    private TextField editNom;
    static CategorieEvenement categorie;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editNom.setText(categorie.getNom_cat_e());  // TODO
    }    
    @FXML
    
    private void btnedit(ActionEvent event) throws IOException {
        String nom = editNom.getText();

        CategorieEvenementService categorieService = new CategorieEvenementService();
        CategorieEvenement updatedCategorie = new CategorieEvenement(categorie.getId(), nom);

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

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Gestioncatevent.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
