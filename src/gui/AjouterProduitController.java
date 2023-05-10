
package gui;

import entities.*;
import services.*;
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
import services.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;


public class AjouterProduitController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField Description;
    @FXML
    private TextField Quantite;
    
    @FXML
    private TextField Prix;
    @FXML
    private ComboBox<String> Categorie;
    @FXML
    private Button btnAjouter;

       private List<categorie> categories = new ArrayList<>();
    File selectedFile;
    String uploadpath;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    categorie_Service service = new categorie_Service();
    
    categories = service.readAll();
        
    for (int i = 0; i < categories.size(); i++){
     Categorie.getItems().add(categories.get(i).getNom());  // TODO
    }
    } 
    
    @FXML
    private void ajouterProduit(ActionEvent event) {
      if (nom.getText().isEmpty()){
            Alert alertType=new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Nom est vide.Ce champ est obligatoire. Veuillez le remplir");
            alertType.show();
            return;
        }
        if (Description.getText().isEmpty()){
            Alert alertType=new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Description est vide.Ce champ est obligatoire. Veuillez le remplir");
            alertType.show();
            return;
        }
        if (!Prix.getText().matches("\\d+")||Prix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être un nombre entier positif non vide .");
            alert.showAndWait();
            return;
        }
        if (Integer.parseInt(Prix.getText()) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être un nombre entier positif.");
            alert.showAndWait();
            return;
        }
        if (!Quantite.getText().matches("\\d+")||Quantite.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("La Quantité doit être un nombre entier positif non vide .");
            alert.showAndWait();
            return;
        }
        if (Integer.parseInt(Quantite.getText()) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("La Quantité doit être un nombre entier positif.");
            alert.showAndWait();
            return;
        }
    
    else {
        try {
            Produit p = new Produit();
            p.setNom_prod(nom.getText());
            p.setDescription(Description.getText());
            p.setPrix(Integer.parseInt(Prix.getText()));
            p.setNbr_prods(Integer.parseInt(Quantite.getText()));
            p.setCAategorie(Categorie.getValue());
            
            if (selectedFile != null) {
                try {
                    File source = new File(selectedFile.toString());
                    File dest = new File("C:\\Users\\don7a\\Desktop\\Pii\\Pi\\gogym_symfony\\public\\uploads\\produit\\");
                   
                    System.out.println(dest);
                    FileUtils.copyFileToDirectory(source, dest);
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
   
            }
            
            p.setImage(uploadpath);
            produit_service e = new produit_service();
            e.add_produit(p);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Produit ajouté avec succès");
            alert.showAndWait();

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
            // Vider les champs
            nom.clear();
            Description.clear();
            Prix.clear();
            Quantite.clear();
           Categorie.getSelectionModel().clearSelection();

        }
        
        catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout du Produit");
            alert.showAndWait();
            ex.printStackTrace();
        }
    }
}

      @FXML
    private void uploadimage(MouseEvent event) throws MalformedURLException {
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
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
    
}
