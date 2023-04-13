/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonnement;
import Services.AbonnementService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HanaM
 */
public class AbonnementController implements Initializable {

    @FXML
    private DatePicker DebutDp;
    @FXML
    private DatePicker FinDp;
    @FXML
    private TextArea DescriptionTa;
    @FXML
    private TextField NomTf;
    @FXML
    private TextField TypeTf;
    @FXML
    private TextField PrixTf;
    @FXML
    private Label NomL;
    @FXML
    private Label TypeL;
    @FXML
    private Label PrixL;
    @FXML
    private Label DescriptionL;
    @FXML
    private Label DebutL;
    @FXML
    private Label FinL;
    @FXML
    private Label TNDL;
    @FXML
    private Label AbonnementL;
    @FXML
    private TableView<Abonnement> AbonnementTv;
    @FXML
    private TableColumn<Abonnement, String> NomC;
    @FXML
    private TableColumn<Abonnement, String> TypeC;
    @FXML
    private TableColumn<Abonnement, Integer> PrixC;
    @FXML
    private TableColumn<Abonnement, String> DescriptionC;
    @FXML
    private TableColumn<Abonnement, Date> DebutC;
    @FXML
    private TableColumn<Abonnement, Date> FinC;

    AbonnementService as = new AbonnementService();

    ObservableList<Abonnement> obs;
    
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModif;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Abonnement> abonnements = as.recuperer();
            obs = FXCollections.observableArrayList(abonnements); //Observablelist il peut detecter les changement 
            AbonnementTv.setItems(obs);
            NomC.setCellValueFactory(new PropertyValueFactory<>("nom_a"));
            TypeC.setCellValueFactory(new PropertyValueFactory<>("type_a"));
            PrixC.setCellValueFactory(new PropertyValueFactory<>("prix_a"));
            DescriptionC.setCellValueFactory(new PropertyValueFactory<>("description_a"));
            DebutC.setCellValueFactory(new PropertyValueFactory<>("debut_a"));
            FinC.setCellValueFactory(new PropertyValueFactory<>("fin_a"));
            
            // ajouter un listener pour sélectionner un abonnement dans le TableView
            AbonnementTv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    NomTf.setText(newValue.getNom_a());
                    TypeTf.setText(newValue.getType_a());
                    PrixTf.setText(String.valueOf(newValue.getPrix_a()));
                    DescriptionTa.setText(newValue.getDescription_a());
                    DebutDp.setValue(newValue.getDebut_a().toLocalDate());
                    FinDp.setValue(newValue.getFin_a().toLocalDate());
                }
            });
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }   
    
    @FXML
    private void CreateAbonnement(ActionEvent event) {
        try {
            String nom_a = NomTf.getText();
            String type_a = TypeTf.getText();
            int prix_a = Integer.parseInt(PrixTf.getText());
            String description_a = DescriptionTa.getText();
            LocalDate debutLocalDate = DebutDp.getValue();
            Date debut_a = Date.valueOf(debutLocalDate);
            LocalDate finLocalDate = FinDp.getValue();
            Date fin_a = Date.valueOf(finLocalDate);

            if (debutLocalDate == null || finLocalDate == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Dates manquantes");
                alert.setHeaderText("Veuillez sélectionner une date de début et une date de fin.");
                alert.showAndWait();
                return;
            }

            Abonnement a = new Abonnement(nom_a, type_a, prix_a, description_a, debut_a, fin_a);
            as.ajouter(a);
            
            // Mettre à jour la liste des abonnements
            AbonnementTv.setItems(FXCollections.observableArrayList(as.recuperer()));
            AbonnementTv.refresh();
        
            // Clear the UI elements
            NomTf.setText("");
            TypeTf.setText("");
            PrixTf.setText("");
            DescriptionTa.setText("");
            DebutDp.setValue(null);
            FinDp.setValue(null);
    
            // Afficher une confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText("L'abonnement a été ajouté avec succès.");
            alert.showAndWait();

        } catch (SQLException | NumberFormatException ex) {
            // Gérer les erreurs liées à la source de données ou au format de nombre
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite lors de l'ajout de l'abonnement.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void DeleteAbonnement(ActionEvent event) {
        try {
            // Vérifier si un élément est sélectionné
            if (AbonnementTv.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun abonnement sélectionné");
            alert.setHeaderText("Veuillez sélectionner un abonnement à supprimer.");
            alert.showAndWait();
            return;
            }
            // Récupérer l'index de l'abonnement sélectionné
            int selectedIndex = AbonnementTv.getSelectionModel().getSelectedIndex();

            // Afficher une confirmation de suppression
            Alert confirmAlert = new Alert(Alert.AlertType.WARNING);
            confirmAlert.setTitle("Confirmation de suppression");
            confirmAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet abonnement ?");
            Optional<ButtonType> result = confirmAlert.showAndWait();

            // Supprimer l'abonnement si l'admin confirme
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Abonnement abonnementASupprimer = obs.get(selectedIndex);
                as.supprimer(abonnementASupprimer);
                obs.remove(selectedIndex);

                // Afficher une confirmation
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Suppression réussie");
                alert.setHeaderText("L'abonnement a été supprimé avec succès.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            // Gérer les erreurs liées à la source de données
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur SQL");
            alert.setHeaderText("Une erreur s'est produite lors de la suppression de l'abonnement.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void UpdateAbonnement(ActionEvent event) {
        try {
            Abonnement selectedAbonnement = AbonnementTv.getSelectionModel().getSelectedItem();
            if (selectedAbonnement != null) {
                int selectedIndex = obs.indexOf(selectedAbonnement);
                String nom_a = NomTf.getText();
                String type_a = TypeTf.getText();
                int prix_a = Integer.parseInt(PrixTf.getText());
                String description_a = DescriptionTa.getText();
                LocalDate debutLocalDate = DebutDp.getValue();
                Date debut_a = Date.valueOf(debutLocalDate);
                LocalDate finLocalDate = FinDp.getValue();
                Date fin_a = Date.valueOf(finLocalDate);

                Abonnement a = new Abonnement(nom_a, type_a, prix_a, description_a, debut_a, fin_a);
                a.setId(selectedAbonnement.getId());
                as.modifier(a);
                obs.set(selectedIndex, a);
                
                // Mettre à jour la liste des abonnements
                AbonnementTv.setItems(FXCollections.observableArrayList(as.recuperer()));
                AbonnementTv.refresh();

                // Clear the UI elements
                NomTf.setText("");
                TypeTf.setText("");
                PrixTf.setText("");
                DescriptionTa.setText("");
                DebutDp.setValue(null);
                FinDp.setValue(null);
            
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aucun abonnement sélectionné");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner un abonnement à modifier.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}