/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Admin;

import entities.Abonnement;
import entities.Salle;
import services.AbonnementService;
import services.SalleService;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML
    private Label DescriptionL1;
    @FXML
    private AnchorPane scrollPane;
    private List<CheckBox> checks = new ArrayList<CheckBox>();
        private List<String> sitesNoms=new ArrayList<String>();
    @FXML
    private TextField entrySearch;
    @FXML
    private ComboBox<String> searchCombo;
    @FXML
    private TableColumn<?, ?> sallesC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchCombo.setValue("Nom");
        searchCombo.getItems().add("Nom");
        
        
        SalleService ss = new SalleService();
        List<Salle> salles;
        try {
            salles = ss.recuperer();
            for (int i = 0;i<salles.size();i++){
                CheckBox check = new CheckBox();
                check.setText(salles.get(i).getNom_s());
                check.setLayoutX(7.0);
                check.setLayoutY(i*24);
                scrollPane.getChildren().add(check);
                checks.add(check);
            }
            List<Abonnement> abonnements = as.recuperer();
            obs = FXCollections.observableArrayList(abonnements); //Observablelist il peut detecter les changement 
            AbonnementTv.setItems(obs);
            NomC.setCellValueFactory(new PropertyValueFactory<>("nom_a"));
            TypeC.setCellValueFactory(new PropertyValueFactory<>("type_a"));
            PrixC.setCellValueFactory(new PropertyValueFactory<>("prix_a"));
            DescriptionC.setCellValueFactory(new PropertyValueFactory<>("description_a"));
            DebutC.setCellValueFactory(new PropertyValueFactory<>("debut_a"));
            FinC.setCellValueFactory(new PropertyValueFactory<>("fin_a"));
            sallesC.setCellValueFactory(new PropertyValueFactory<>("salles"));
            // ajouter un listener pour sélectionner un abonnement dans le TableView
            AbonnementTv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                sitesNoms=new ArrayList<String>();
                if (newValue != null) {
                    NomTf.setText(newValue.getNom_a());
                    TypeTf.setText(newValue.getType_a());
                    PrixTf.setText(String.valueOf(newValue.getPrix_a()));
                    DescriptionTa.setText(newValue.getDescription_a());
                    DebutDp.setValue(newValue.getDebut_a().toLocalDate());
                    FinDp.setValue(newValue.getFin_a().toLocalDate());
                    
                    try {
                        List<Salle> abonnementSalles = as.getSallesAbonnement(newValue);
                        checks.stream().filter(c -> abonnementSalles.stream().anyMatch(s2 -> c.getText().equals(s2.getNom_s())))
                            .forEach(c -> {
                                sitesNoms.add(c.getText());
                                c.setSelected(true);
                            });
                        checks.stream().filter(c -> abonnementSalles.stream().allMatch(s2 -> !c.getText().equals(s2.getNom_s())))
                            .forEach(c -> {
                                c.setSelected(false);
                            });
                    } catch (SQLException ex) {
                        Logger.getLogger(AbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
            
            if(NomTf.getText().isEmpty() ){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nom manquant");
                alert.setHeaderText("Veuillez saisir un nom.");
                alert.showAndWait();
                return;
            }
            
            if(TypeTf.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("type manquant");
                alert.setHeaderText("Veuillez sélectionner le type de cette salle !");
                alert.showAndWait();
                return;
            } else if (!TypeTf.getText().equals("annuel") && !TypeTf.getText().equals("trimestriel") && !TypeTf.getText().equals("semestriel") && !TypeTf.getText().equals("mensuel")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("type invalide");
                alert.setHeaderText("Le type de l'abonnement doit être annuel, trimestriel, semestriel, mensuel !");
                alert.showAndWait();
                return;
            }

            if(PrixTf.getText().isEmpty() ){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Prix manquant");
                alert.setHeaderText("Veuillez saisir un prix.");
                alert.showAndWait();
                return;
            }

            if (debutLocalDate == null || finLocalDate == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Dates manquantes");
                alert.setHeaderText("Veuillez sélectionner une date de début et une date de fin.");
                alert.showAndWait();
                return;
            }

            List<String> sallesChecked = new ArrayList<String>();

            for (Node node : scrollPane.getChildren()) {
                if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    if (checkBox.isSelected()){
                        sallesChecked.add(checkBox.getText());
                    }
                }
            }
                
            Abonnement a = new Abonnement(nom_a, type_a, prix_a, description_a, debut_a, fin_a);
            as.ajouter(a, sallesChecked);
            
            //Mettre à jour la liste des abonnements
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
            
                if(NomTf.getText().isEmpty() ){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Nom manquant");
                    alert.setHeaderText("Veuillez saisir un nom.");
                    alert.showAndWait();
                    return;
                }

                if(TypeTf.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("type manquant");
                    alert.setHeaderText("Veuillez sélectionner le type de cette salle !");
                    alert.showAndWait();
                    return;
                } else if (!TypeTf.getText().equals("annuel") && !TypeTf.getText().equals("trimestriel") && !TypeTf.getText().equals("semestriel") && !TypeTf.getText().equals("mensuel")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("type invalide");
                    alert.setHeaderText("Le type de l'abonnement doit être annuel, trimestriel, semestriel, mensuel !");
                    alert.showAndWait();
                    return;
                }

                if(PrixTf.getText().isEmpty() ){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Prix manquant");
                    alert.setHeaderText("Veuillez saisir un prix.");
                    alert.showAndWait();
                    return;
                }

                if (debutLocalDate == null || finLocalDate == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Dates manquantes");
                    alert.setHeaderText("Veuillez sélectionner une date de début et une date de fin.");
                    alert.showAndWait();
                    return;
                }
            
                Abonnement a = new Abonnement(nom_a, type_a, prix_a, description_a, debut_a, fin_a);
                a.setId(selectedAbonnement.getId());
                
                List<String> sitesAdded = new ArrayList<String>();
                List<String> sitesDelete = new ArrayList<String>();

                for (Node node : scrollPane.getChildren()) {
                    if (node instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) node;
                        if (checkBox.isSelected() && !sitesNoms.contains(checkBox.getText())){
                            sitesAdded.add(checkBox.getText());
                        }
                        else if (!checkBox.isSelected() && sitesNoms.contains(checkBox.getText())){
                            sitesDelete.add(checkBox.getText());
                        }
                    }
                }
                
                as.modifier(a, sitesAdded, sitesDelete);
                obs.set(selectedIndex, a);
                
                // Mettre à jour la liste des abonnements
                //AbonnementTv.setItems(FXCollections.observableArrayList(as.recuperer()));
                //AbonnementTv.refresh();

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
            ex.printStackTrace();
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

                // Mettre à jour la liste des abonnements
                //AbonnementTv.setItems(FXCollections.observableArrayList(as.recuperer()));
                //AbonnementTv.refresh();

                // Clear the UI elements
                NomTf.setText("");
                TypeTf.setText("");
                PrixTf.setText("");
                DescriptionTa.setText("");
                DebutDp.setValue(null);
                FinDp.setValue(null);
            
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
    
    private void Retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GoGym.fxml"));
            Parent root = loader.load();
            
            GoGymController controller = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            primaryStage.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void search(ActionEvent event) throws SQLException {
        String searchPar = searchCombo.getValue();
        String ch = "";
        if (searchPar.equals("Nom")){
            ch="nom_a";
        }
        String entry = entrySearch.getText();
        List<Abonnement> abonnementsSearch = as.recupererSearchPar(entry, ch);
        obs = FXCollections.observableArrayList(abonnementsSearch); //Observablelist il peut detecter les changement 
               AbonnementTv.setItems(obs);
    }

    @FXML
    private void toPDF(ActionEvent event) throws SQLException, DocumentException, Exception {
        List<Abonnement> abonnements = as.recuperer();
        as.toPDF(abonnements);
    }

    @FXML
    private void Event(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("Gestionevenement.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();

    }

    @FXML
    private void Categorie(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("Gestioncatevent.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    private void Abonnement(ActionEvent event) throws IOException {
      
    }

    @FXML
    private void Salle(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("/gui/Admin/Salle.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    private void Cours(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("/gui/cours.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    private void Reservation(ActionEvent event) throws IOException {

        Parent root1 = FXMLLoader.load(getClass().getResource("/gui/reservation.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }

     @FXML
    private void GestionProd(ActionEvent event) throws IOException {

        Parent root1 = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    } 
  
  
  
     @FXML
  private void Gestioncategorie(ActionEvent event) throws IOException {

        Parent root1 = FXMLLoader.load(getClass().getResource("GestionCatProd.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    } 
    
}
