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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.json.JSONException;
import org.json.JSONObject;

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
    String[] words = {"ghazela", "ariana", "menzah", "nasser"};
     Set<String> possibleWordSet = new HashSet<>();
    AutoCompletionBinding<String> autoCompletionBinding;

       private List<CategorieEvenement> categories = new ArrayList<>();
    File selectedFile;
    String uploadpath;
    String AIP = null;
    String pays;
    String region;
    String idn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       try {
    URL url_name = new URL("http://checkip.amazonaws.com/");
    BufferedReader bf = new BufferedReader(new InputStreamReader(url_name.openStream()));
    String AIP = bf.readLine().trim();
    System.out.println(AIP);

    JSONObject json = readJsonFromUrl("https://api.ipgeolocation.io/ipgeo?apiKey=e3f347b989f34e239402188106fbdf4c&ip=" + AIP);
    System.out.println(json.toString());

    String pays = json.optString("country_name", "");
    String region = json.optString("city", "");
    String idn = json.optString("calling_code", "");

    System.out.println("pays" + pays);
    System.out.println("region" + region);
    System.out.println(idn);

    // Crée un objet FileWriter et BufferedWriter pour écrire dans un fichier texte
    FileWriter fw = new FileWriter("monfichier.txt");
    BufferedWriter bw = new BufferedWriter(fw);

    // Écrit les informations dans le fichier texte
    bw.write("AIP : " + AIP);
    bw.newLine();
    bw.write("Pays : " + pays);
    bw.newLine(); // Ajoute une nouvelle ligne
    bw.write("Région : " + region);
    bw.newLine();
    bw.write("Indicatif téléphonique : " + idn);

    // Ferme le BufferedWriter et le FileWriter
    bw.close();
    fw.close();

    System.out.println("Les informations ont été écrites dans le fichier monfichier.txt !");
} catch (Exception e) {
    System.out.println("Erreur lors de la récupération des informations : " + e.getMessage());
}
 
     
     
    CategorieEvenementService service = new CategorieEvenementService();
    
    categories = service.readAll();
    
    
    
       Collections.addAll(possibleWordSet, words);
        autoCompletionBinding = TextFields.bindAutoCompletion(tfLieu, possibleWordSet);

        tfLieu.setOnKeyPressed(
                (KeyEvent e) -> {
                    switch (e.getCode()) {
                        case ENTER:
                            learnWord((tfLieu.getText()));
                            break;
                        default:
                            break;
                    }
                }
        );
    
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
    
    
    
    private void learnWord(String text) {
        possibleWordSet.add(text);
        if (autoCompletionBinding != null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(tfLieu, possibleWordSet);

    }


  public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Gestionevenement.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
  
  
   private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    
}


 