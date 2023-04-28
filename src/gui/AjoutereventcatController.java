package gui;

import Entities.CategorieEvenement;
import Services.CategorieEvenementService;
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

public class AjoutereventcatController implements Initializable {

    @FXML
    private TextField tfNomCat;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnRetour;

    private CategorieEvenementService ces;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ces = new CategorieEvenementService();
    }

    public void ajouter(ActionEvent event) throws SQLException {
        String nom = tfNomCat.getText();
        if (nom == null || nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Nom de catégorie invalide");
            alert.setContentText("Le nom de catégorie ne doit pas être vide !");
            alert.showAndWait();
            return;
        }
        ces.ajouterCategorieEvenement(nom);
        clearFields();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Catégorie ajoutée avec succès !");
        alert.showAndWait();
    }

    private void clearFields() {
        tfNomCat.setText("");
    }

    public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Gestioncatevent.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
}
