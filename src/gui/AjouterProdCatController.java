package gui;

import entities.*;
import services.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterProdCatController implements Initializable {

    @FXML
    private TextField NomCat;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRetour;

    private categorie_Service ces;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ces = new categorie_Service();
    }

    public void ajouter(ActionEvent event) throws SQLException, IOException  {
        String nom = NomCat.getText();
       if (NomCat.getText().isEmpty()){
            Alert alertType=new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Le nom est vide.Ce champ est obligatoire. Veuillez le remplir");
            alertType.show();
            return;
        }
        categorie existCategorie = ces.readByNom(NomCat.getText());
        if (existCategorie != null){
            Alert alertType=new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Erreur");
            alertType.setHeaderText("Cette catégorie existe déjà. Veuillez entrer un nom de catégorie différent.");
            alertType.show();
            return;
        }
        categorie e = new categorie(nom);
        ces.insert(e);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Catégorie ajoutée avec succès !");
        alert.showAndWait();
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionCatProd.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

        

 

    public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionCatProd.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
}
