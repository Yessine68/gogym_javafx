/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.CategorieEvenement;
import Entities.Evenement;
import Services.CategorieEvenementService;
import Services.EvenementService;
import static gui.ModifierEvenementController.evenement;
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

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ModifierEvenementController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfLieu;
    @FXML
    private TextField tfNbrParticipants;
    @FXML
    private ComboBox<String> cbcategorie;
    
    private List<CategorieEvenement> categories = new ArrayList<>();
    public static Evenement evenement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    CategorieEvenementService service = new CategorieEvenementService();
    
    categories = service.readAll();
        
    for (int i = 0; i < categories.size(); i++){
     cbcategorie.getItems().add(categories.get(i).getNom_cat_e()); 
    }
        // Pré-remplissage des champs avec les informations de l'événement à modifier
        tfNom.setText(evenement.getNom_e());
        tfDescription.setText(evenement.getDescription_e());
        tfDate.setText(evenement.getDate_e());
        tfLieu.setText(evenement.getLieu_e());
        tfNbrParticipants.setText(String.valueOf(evenement.getNbr_participants()));
        cbcategorie.setValue(evenement.getCategorieEvenement());
        // TODO
     
    }
    
    @FXML
     void modifierEvenement(ActionEvent event) throws IOException {
        // Récupération des données saisies par l'utilisateur
        String nom = tfNom.getText();
        String description = tfDescription.getText();
        String date = tfDate.getText();
        String lieu = tfLieu.getText();
        int nbrParticipants = Integer.parseInt(tfNbrParticipants.getText());
        String categorie = cbcategorie.getValue();
        
        
        if (tfNom.getText().isEmpty() || tfLieu.getText().isEmpty() || tfDescription.getText().isEmpty() || tfDate.getText() == null || tfNbrParticipants.getText().isEmpty() || cbcategorie.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
    } else {
        try {
            Evenement e = new Evenement();
            e.setNom_e(tfNom.getText());
            e.setLieu_e(tfLieu.getText());
            e.setDescription_e(tfDescription.getText());
            e.setDate_e(tfDate.getText());
            e.setNbr_participants(Integer.parseInt(tfNbrParticipants.getText()));
            e.setCategorieEvenement(cbcategorie.getValue());
            e.setId(evenement.getId());
            e.setImage(evenement.getImage());
            EvenementService se = new EvenementService();
            se.update(e);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Evenement MOdifier avec succès");
            alert.showAndWait();

            // Vider les champs
            tfNom.clear();
            tfLieu.clear();
            tfDescription.clear();
            tfDate.clear();
            tfNbrParticipants.clear();
            cbcategorie.getSelectionModel().clearSelection();

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout de l'evenement");
            alert.showAndWait();
            ex.printStackTrace();

        // Vérification de la validité des données saisies
       /* if (nom.isEmpty() || description.isEmpty() || date.isEmpty() || lieu.isEmpty() || categorie == null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;  */
       
       
       
       
        }
    
    }
    
   Parent tableViewParent = FXMLLoader.load(getClass().getResource("Gestionevenement.fxml"));
            Scene tabbleViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tabbleViewScene);
            window.show();
    }
    
    
    
    
    
}
