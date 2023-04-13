/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.CategorieEvenement;
import Entities.Evenement;
import Services.EvenementService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Services.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterEvenementController implements Initializable {

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
    @FXML
    private Button btnAjouter;

       private List<CategorieEvenement> categories = new ArrayList<>();
    File selectedFile;
    String uploadpath;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    CategorieEvenementService service = new CategorieEvenementService();
    
    categories = service.readAll();
        
    for (int i = 0; i < categories.size(); i++){
     cbcategorie.getItems().add(categories.get(i).getNom_cat_e());  // TODO
    }
    } 
    
    @FXML
    private void ajouterEvenement(ActionEvent event) {
    /*if (tfNom.getText().isEmpty() || tfLieu.getText().isEmpty() || tfDescription.getText().isEmpty() || tfDate.getText() == null || tfNbrParticipants.getText().isEmpty() || cbcategorie.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
    } */
    
    if (tfNom.getText().isEmpty()){
        Alert alertType=new Alert(Alert.AlertType.ERROR);
        alertType.setTitle("Error");
        alertType.setHeaderText("Nom est vide.Ce champ est obligatoire. Veuillez le remplir");
        alertType.show();
        return;
    }
    if (tfDescription.getText().isEmpty()){
        Alert alertType=new Alert(Alert.AlertType.ERROR);
        alertType.setTitle("Error");
        alertType.setHeaderText("Description est vide.Ce champ est obligatoire. Veuillez le remplir");
        alertType.show();
        return;
    }
    if (!tfNbrParticipants.getText().matches("\\d+")||tfNbrParticipants.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Le nbparticipants doit être un nombre entier positif non vide .");
        alert.showAndWait();
        return;
    }
    if (Integer.parseInt(tfNbrParticipants.getText()) <= 0) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Le nbparticipants doit être un nombre entier positif.");
        alert.showAndWait();
        return;
    }
    if (tfLieu.getText().isEmpty()){
        Alert alertType=new Alert(Alert.AlertType.ERROR);
        alertType.setTitle("Error");
        alertType.setHeaderText("Lieu est vide.Ce champ est obligatoire. Veuillez le remplir");
        alertType.show();
        return;
    }
  if (tfDate.getText().isEmpty()){
        Alert alertType=new Alert(Alert.AlertType.ERROR);
        alertType.setTitle("Error");
        alertType.setHeaderText("Date est vide.Ce champ est obligatoire. Veuillez le remplir");
        alertType.show();
        return;
    }
 
  if (cbcategorie.getSelectionModel().isEmpty()){
        Alert alertType=new Alert(Alert.AlertType.ERROR);
        alertType.setTitle("Error");
        alertType.setHeaderText("categorie est vide.Ce champ est obligatoire. Veuillez le remplir");
        alertType.show();
        return;
    }
    
    
    
    else {
        try {
            Evenement e = new Evenement();
            e.setNom_e(tfNom.getText());
            e.setLieu_e(tfLieu.getText());
            e.setDescription_e(tfDescription.getText());
            e.setDate_e(tfDate.getText());
            e.setNbr_participants(Integer.parseInt(tfNbrParticipants.getText()));
            e.setCategorieEvenement(cbcategorie.getValue());
            e.setImage(uploadpath);
            EvenementService se = new EvenementService();
            se.ajouterEvenement(e);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Evenement ajouté avec succès");
            alert.showAndWait();

            // Vider les champs
            tfNom.clear();
            tfLieu.clear();
            tfDescription.clear();
            tfDate.clear();
            tfNbrParticipants.clear();
            cbcategorie.getSelectionModel().clearSelection();

        }
        
        catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout de l'evenement");
            alert.showAndWait();
            ex.printStackTrace();
        }
    }
}

      @FXML
    private void uploadimage(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            uploadpath = selectedFile.getName();
            System.out.println(uploadpath);
        }
    }

  public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Gestionevenement.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
    
}
