package pages;

import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.categorie_Service;
import entities.categorie;

public class AjouterCategorie {
    categorie_Service categorie_service  = new categorie_Service();

    @FXML
    private TextField nom_categorie;

    @FXML
    void ajoutercategorie(ActionEvent event) {
        if (nom_categorie.getText().isEmpty()){
            Alert alertType=new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("Le nom est vide.Ce champ est obligatoire. Veuillez le remplir");
            alertType.show();
            return;
        }
        categorie existCategorie = categorie_service.readByNom(nom_categorie.getText());
        if (existCategorie != null){
            Alert alertType=new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Erreur");
            alertType.setHeaderText("Cette catégorie existe déjà. Veuillez entrer un nom de catégorie différent.");
            alertType.show();
            return;
        }

        String nomCate = nom_categorie.getText();
        categorie categorie = new categorie(nomCate);
        categorie_service.insert(categorie);

        Alert alertType=new Alert(Alert.AlertType.INFORMATION);
        alertType.setTitle("Confirmation");
        alertType.setHeaderText("La catégorie a été ajoutée avec succès");
        alertType.show();
    }

}
