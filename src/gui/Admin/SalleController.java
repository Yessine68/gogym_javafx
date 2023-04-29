/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Admin;

import entities.Salle;
import services.SalleService;
import utils.Variables;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HanaM
 */
public class SalleController implements Initializable {

    private TextField NomTf;
    private TextField EmailTf;
    private TextField TelTf;
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

    SalleService ss = new SalleService();
    
    ObservableList<Salle> obs;
    @FXML
    private Button retourbtns;
private boolean selectImage=false;
    @FXML
    private TableColumn<?, ?> LongitudeC;
    @FXML
    private TableColumn<?, ?> LatitudeC;
    @FXML
    private TableColumn<?, ?> AbonnementsC;
    @FXML
    private TextField entrySearch;
    @FXML
    private ComboBox<String> searchCombo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            searchCombo.setValue("Nom");
            searchCombo.getItems().add("Nom");
            searchCombo.getItems().add("Ville");
            searchCombo.getItems().add("Email");
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
            AbonnementsC.setCellValueFactory(new PropertyValueFactory<>("abonnements"));
            LongitudeC.setCellValueFactory(new PropertyValueFactory<>("longitude_s"));
            LatitudeC.setCellValueFactory(new PropertyValueFactory<>("latitude_s"));
            // ajouter un listener pour sélectionner un salle dans le TableView
            SalleTv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                Variables.salleDetail=newValue;
            });
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }    

    @FXML
    private void CreateSalle(ActionEvent event) throws FileNotFoundException, IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddSalle.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage2 = new Stage();
            primaryStage2.setTitle("Ajout Salle");
            primaryStage2.setScene(scene);
            primaryStage2.setScene(scene);
            primaryStage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @FXML
    private void UpdateSalle(ActionEvent event) throws IOException {
        if (Variables.salleDetail!=null){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ModifierSalle.fxml"));
                Scene scene = new Scene(root);
                Variables.stagee.setTitle("Modifier Salle");
                Variables.stagee.setScene(scene);
                Variables.stagee.setScene(scene);
                Variables.stagee.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
                
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune salle sélectionnée");
            alert.setContentText("Veuillez sélectionner une salle pour la modifier.");
            alert.showAndWait();
            return;
        }
    }
    
    @FXML
    private void DeleteSalle(ActionEvent event) throws SQLException {
        if (Variables.salleDetail!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to delete this Salle?");
            alert.setContentText("This action cannot be undone.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ss.supprimer(Variables.salleDetail);
                Variables.salleDetail=null;
                List<Salle> salles = ss.recuperer();
                obs = FXCollections.observableArrayList(salles); //Observablelist il peut detecter les changement 
                SalleTv.setItems(obs);
                //primaryStage.setScene(new Scene(root));
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune salle sélectionnée");
            alert.setContentText("Veuillez sélectionner une salle pour la modifier.");
            alert.showAndWait();
            return;
        }
    }

    public boolean isValidEmail(String email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        boolean isValid = false;
        String expression = "^[0-9]{8}$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @FXML
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
            ch="nom_s";
        }
        if (searchPar.equals("Ville")){
            ch="ville_s";
        }
        if (searchPar.equals("Email")){
            ch="email_s";
        }
        String entry = entrySearch.getText();
        List<Salle> salleSearch = ss.recupererSearchPar(entry, ch);
        obs = FXCollections.observableArrayList(salleSearch); //Observablelist il peut detecter les changement 
                SalleTv.setItems(obs);
    }
    
    @FXML
    private void toPDF(ActionEvent event) throws DocumentException, SQLException {
        List<Salle> salles = ss.recuperer();
        ss.toPDF(salles);
    }
    
}
