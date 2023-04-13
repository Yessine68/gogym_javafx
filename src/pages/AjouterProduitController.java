package pages;

import entities.Produit;
import entities.categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import services.produit_service;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import services.*;

public class AjouterProduitController  implements Initializable {
    produit_service produitService = new produit_service();
    @FXML
    private TextField nom_produit;

    @FXML
    private TextField prix_produit;

    @FXML
    private Button btnAjouteEven;

    @FXML
    private TextField nbr_produit;

    @FXML
    private TextField description_produit;

    @FXML
    private ComboBox<String> cat;

    @FXML
    private Button img;

    String image;

    File selectedFile;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        categorie_Service service = new categorie_Service();
        ObservableList<categorie> list = FXCollections.observableArrayList();
        list = service.readAll();
        System.out.print(list);
        for (int i = 0; i < list.size(); i++){
            cat.getItems().add(list.get(i).getNom() );
        }
    }
    @FXML
    void ajouterProduit(ActionEvent event) {

        if (nom_produit.getText().isEmpty()){
            Alert alertType=new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Nom est vide.Ce champ est obligatoire. Veuillez le remplir");
            alertType.show();
            return;
        }
        if (description_produit.getText().isEmpty()){
            Alert alertType=new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Description est vide.Ce champ est obligatoire. Veuillez le remplir");
            alertType.show();
            return;
        }
        if (!prix_produit.getText().matches("\\d+")||prix_produit.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être un nombre entier positif non vide .");
            alert.showAndWait();
            return;
        }
        if (Integer.parseInt(prix_produit.getText()) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être un nombre entier positif.");
            alert.showAndWait();
            return;
        }
        if (!nbr_produit.getText().matches("\\d+")||nbr_produit.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("La Quantité doit être un nombre entier positif non vide .");
            alert.showAndWait();
            return;
        }
        if (Integer.parseInt(nbr_produit.getText()) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("La Quantité doit être un nombre entier positif.");
            alert.showAndWait();
            return;
        }

        String nomProduit = nom_produit.getText();
            String description = description_produit.getText();
            int prix = Integer.parseInt(prix_produit.getText());
            int nbpProduit = Integer.parseInt(nbr_produit.getText());
           String categorie = cat.getValue();

            Produit produit = new Produit(nomProduit,description,prix,nbpProduit,categorie,image);
            produitService.add_produit(produit);

        Alert alertType=new Alert(Alert.AlertType.INFORMATION);
        alertType.setTitle("Confirmation");
        alertType.setHeaderText("Le Produit a été ajoutée avec succès");
        alertType.show();
    }
    @FXML
    void btnimg(MouseEvent event)  {



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

            image = selectedFile.getName();
            System.out.println(image);
        }
    }
}
