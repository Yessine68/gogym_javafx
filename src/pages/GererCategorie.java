package pages;

import entities.Produit;
import entities.categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.categorie_Service;
import services.produit_service;

import java.net.URL;
import java.util.ResourceBundle;

public class GererCategorie implements Initializable {
    categorie_Service categorie_service = new categorie_Service();
    ObservableList<categorie> list = FXCollections.observableArrayList();


    @FXML
    private TextField id_categorie_modif;

    @FXML
    private TableColumn<categorie, Integer> id_produit;

    @FXML
    private TextField nom_categorie_modif;

    @FXML
    private TableColumn<categorie, String> nom_produit;

    @FXML
    private TableView<categorie> table_categorie;

    @FXML
    void Modif(ActionEvent event) {
        String nomCate = nom_categorie_modif.getText();
        int IDcategorie = Integer.parseInt(id_categorie_modif.getText());
        categorie_Service categorie_service1  = new categorie_Service();
        categorie categorie1 = new categorie(IDcategorie, nomCate);
        System.out.println(categorie1);
        categorie_service1.update(categorie1);
        showEventTable();
    }

    @FXML
    void delete(ActionEvent event) {
        int IDcategorie = Integer.parseInt(id_categorie_modif.getText());
        categorie_Service categorie_service1  = new categorie_Service();
        categorie_service1.delete(IDcategorie);
        showEventTable();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < categorie_service.readAll().size(); i++) {

            list.addAll(categorie_service.readAll().get(i));
        }
        addListenerEvent();
        showEventTable();
    }

    private void addListenerEvent() {

        table_categorie.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                id_categorie_modif.setText(String.valueOf(newSelection.getId()));
                nom_categorie_modif.setText(String.valueOf(newSelection.getNom()));
            } else {
                id_categorie_modif.setText("");
                id_categorie_modif.setText(String.valueOf(newSelection.getId()));
                nom_categorie_modif.setText("");
                nom_categorie_modif.setText(String.valueOf(newSelection.getNom()));

            }
        });

    }
    public void showEventTable(){
        ObservableList<categorie> list = getCategorieList();
        id_produit.setCellValueFactory(new PropertyValueFactory<categorie, Integer>("id"));
        nom_produit.setCellValueFactory(new PropertyValueFactory<categorie, String>("nom"));
        table_categorie.setItems(list);
    }

    private ObservableList<categorie> getCategorieList() {
        ObservableList<categorie>categories = FXCollections.observableArrayList();
        categorie_Service categorie_service = new categorie_Service();
        categories = categorie_service.readAll();
        return categories;
    }
}
