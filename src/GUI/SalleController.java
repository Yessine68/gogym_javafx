/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Salle;
import Entities.Salle;
import Services.SalleService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HanaM
 */
public class SalleController implements Initializable {

    @FXML
    private TextField NomTf;
    @FXML
    private TextField EmailTf;
    @FXML
    private TextField TelTf;
    @FXML
    private Label NomL;
    @FXML
    private Label EmailL;
    @FXML
    private Label TelL;
    @FXML
    private Label AdresseL;
    @FXML
    private Label VilleL;
    @FXML
    private Label ImageL;
    @FXML
    private Label tel216L;
    @FXML
    private Label SalleL;
    @FXML
    private TableView<Salle> SalleTv;
    @FXML
    private TableColumn<Salle, String> NomC;
    @FXML
    private TableColumn<Salle, String> EmailC;
    @FXML
    private TableColumn<Salle, Integer> TelC;
    @FXML
    private TableColumn<Salle, String> AdresseC;
    @FXML
    private TableColumn<Salle, String> VilleC;
    @FXML
    private TableColumn<Salle, String> ImageC;
    @FXML
    private TableColumn<Salle, Float> PerimetreC;
    @FXML
    private TableColumn<Salle, Integer> lIkeC;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModif;
    @FXML
    private Label PerimetreL;
    @FXML
    private Label LikeL;
    @FXML
    private TextField VilleTf;
    @FXML
    private TextField AdresseTf;
    @FXML
    private TextField LikeTf;
    @FXML
    private Label m2L;
    @FXML
    private Label LikesL;

    SalleService ss = new SalleService();
    
    ObservableList<Salle> obs;
    @FXML
    private TextField ImageTf;
    @FXML
    private TextField PerimetreTf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Salle> salles = ss.recuperer();
            obs = FXCollections.observableArrayList(salles); //Observablelist il peut detecter les changement 
            SalleTv.setItems(obs);
            NomC.setCellValueFactory(new PropertyValueFactory<>("nom_s"));
            EmailC.setCellValueFactory(new PropertyValueFactory<>("email_s"));
            TelC.setCellValueFactory(new PropertyValueFactory<>("tel_s"));
            AdresseC.setCellValueFactory(new PropertyValueFactory<>("adresse_s"));
            VilleC.setCellValueFactory(new PropertyValueFactory<>("ville_s"));
            ImageC.setCellValueFactory(new PropertyValueFactory<>("image_s"));
            PerimetreC.setCellValueFactory(new PropertyValueFactory<>("perimetre_s"));
            lIkeC.setCellValueFactory(new PropertyValueFactory<>("like_s"));

            // ajouter un listener pour sélectionner un salle dans le TableView
            SalleTv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    NomTf.setText(newValue.getNom_s());
                    EmailTf.setText(newValue.getEmail_s());
                    TelTf.setText(Integer.toString(newValue.getTel_s()));
                    AdresseTf.setText(newValue.getAdresse_s());
                    VilleTf.setText(newValue.getVille_s());
                    ImageTf.setText(newValue.getImage_s());
                    PerimetreTf.setText(Float.toString(newValue.getPerimetre_s()));
                    LikeTf.setText(Integer.toString(newValue.getLike_s()));
                }
            });
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }    

    @FXML
    private void CreateSalle(ActionEvent event) {
        try {
            String nom_s = NomTf.getText();
            String email_s = EmailTf.getText();
            int tel_s = Integer.parseInt(TelTf.getText());
            String adresse_s = AdresseTf.getText();
            String ville_s = VilleTf.getText();
            String image_s = ImageTf.getText();
            float perimetre_s = Float.parseFloat(PerimetreTf.getText());
            int like_s = Integer.parseInt(LikeTf.getText());

            if (adresse_s == null || ville_s == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Adresse manquante");
                alert.setHeaderText("Veuillez saisir l'adresse de cette salle !");
                alert.showAndWait();
                return;
            }

            Salle s = new Salle(nom_s, email_s, tel_s, adresse_s, ville_s, image_s, perimetre_s, like_s);
            ss.ajouter(s);
            
            // Mettre à jour la liste des salles
            SalleTv.setItems(FXCollections.observableArrayList(ss.recuperer()));
            SalleTv.refresh();
        
            // Clear the UI elements
            NomTf.setText("");
            EmailTf.setText("");
            TelTf.setText("");
            AdresseTf.setText("");
            VilleTf.setText("");
            ImageTf.setText("");
            PerimetreTf.setText("");
            LikeTf.setText("");
                    
            // Afficher une confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText("La salle été ajouté avec succès.");
            alert.showAndWait();

        } catch (SQLException | NumberFormatException ex) {
            // Gérer les erreurs liées à la source de données ou au format de nombre
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite lors de l'ajout de la salle .");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void DeleteSalle(ActionEvent event) {
        try {
            // Vérifier si un élément est sélectionné
            if (SalleTv.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun salle sélectionné");
            alert.setHeaderText("Veuillez sélectionner un salle à supprimer.");
            alert.showAndWait();
            return;
            }
            // Récupérer l'index de la salle sélectionné
            int selectedIndex = SalleTv.getSelectionModel().getSelectedIndex();

            // Afficher une confirmation de suppression
            Alert confirmAlert = new Alert(Alert.AlertType.WARNING);
            confirmAlert.setTitle("Confirmation de suppression");
            confirmAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette salle ?");
            Optional<ButtonType> result = confirmAlert.showAndWait();

            // Supprimer la salle si l'admin confirme
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Salle salleASupprimer = obs.get(selectedIndex);
                ss.supprimer(salleASupprimer);
                obs.remove(selectedIndex);

                // Afficher une confirmation
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Suppression réussie");
                alert.setHeaderText("La salle été supprimé avec succès.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            // Gérer les erreurs liées à la source de données
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur SQL");
            alert.setHeaderText("Une erreur s'est produite lors de la suppression de la salle.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void UpdateSalle(ActionEvent event) {
        try {
            Salle selectedSalle = SalleTv.getSelectionModel().getSelectedItem();
            if (selectedSalle != null) {
                int selectedIndex = obs.indexOf(selectedSalle);
                String nom_s = NomTf.getText();
                String email_s = EmailTf.getText();
                int tel_s = Integer.parseInt(TelTf.getText());
                String adresse_s = AdresseTf.getText();
                String ville_s = VilleTf.getText();
                String image_s = ImageTf.getText();
                float perimetre_s = Float.parseFloat(PerimetreTf.getText());
                int like_s = Integer.parseInt(LikeTf.getText());
                Salle s = new Salle(nom_s, email_s, tel_s, adresse_s, ville_s, image_s, perimetre_s, like_s);
                s.setId(selectedSalle.getId());
                ss.modifier(s);
                obs.set(selectedIndex, s);
                
                // Mettre à jour la liste des salles
                SalleTv.setItems(FXCollections.observableArrayList(ss.recuperer()));
                SalleTv.refresh();

                // Clear the UI elements
                NomTf.setText("");
                EmailTf.setText("");
                TelTf.setText("");
                AdresseTf.setText("");
                VilleTf.setText("");
                ImageTf.setText("");
                PerimetreTf.setText("");
                LikeTf.setText("");

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aucune salle sélectionnée");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner une salle à modifier.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
