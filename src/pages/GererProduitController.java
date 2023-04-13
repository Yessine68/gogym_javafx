/*
import Entity.Evenement;
import Service.ServiceEvenement;
import Util.DataSource;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class GererProduitController implements Initializable {

    ServiceEvenement serviceEvenement = new ServiceEvenement(cnx);

    ObservableList<Evenement> list = FXCollections.observableArrayList();


    @FXML
    private TextField nom_modif;
    @FXML
    private TextField lieu_modif;
    @FXML
    private DatePicker datemodif;
    @FXML
    private VBox chosenOffreCard;



    private SupprimerCard sup;

    private Modifier modif;

    @FXML
    private TableColumn<Evenement, String> date_event;

    @FXML
    private ImageView fruitImg;

    @FXML
    private GridPane grid;

    Evenement evenement1;

    private int i=0;

    @FXML
    private TextField keywordTextField;

    private Button btnmodif;
    private Button btnsupprimer;

    @FXML
    private TableColumn<Evenement, String> lieu_event;

    @FXML
    private TableColumn<Evenement, String> nom_event;

    @FXML
    private TableView<Evenement> table_evenement;

    @FXML
    private TextField nbrPlace_modif;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for (int i = 0; i < serviceEvenement.readAll().size(); i++) {

            list.addAll(serviceEvenement.readAll().get(i));
        }

        FilteredList<Evenement> filteredData = new FilteredList<>(list, b -> true);

        keywordTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            filteredData.setPredicate(e -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (e.getNom().toLowerCase().startsWith(searchKeyword)) {
                    return true;
                } else if (e.getLieu().toLowerCase().startsWith(searchKeyword)) {
                    return true;
                } else if (String.valueOf(e.getDate()).toLowerCase().startsWith(searchKeyword)) {
                    return true;
                } else {
                    return false;
                }

            });

            SortedList<Evenement> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(table_evenement.comparatorProperty());

            table_evenement.setItems(sortedData);
        });

        addListenerEvent();
        showEventTable();
        int column = 1;
        int row = 0;

        for (int i = 0; i < serviceEvenement.readAll().size(); i++) {

            try {

                sup = new SupprimerCard() {
                    @Override
                    public void supprimer(Evenement evenement) {
                        supprimerr(evenement);
                    }
                };
                modif = new Modifier() {
                    @Override
                    public void modifier(Evenement evenement) {
                        modifierr(evenement);
                    }

                };
                FXMLLoader cards = new FXMLLoader();
                cards.setLocation(getClass().getResource("event_card.fxml"));

                AnchorPane anchorPane = cards.load();

                eventCardController cardController = cards.getController();

                cardController.setData(serviceEvenement.readAll().get(i), sup, modif);

                //       offreservice.setData(offre, supp);
                if (column == 3) {
                    column = 1;
                    row++;

                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new javafx.geometry.Insets(20));

////
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    @FXML
    void Modif(ActionEvent event) {

        String lieuE = lieu_modif.getText();
        String nomE = nom_modif.getText();
        int nbrPalace = Integer.parseInt(nbrPlace_modif.getText());
        java.sql.Date dateE = java.sql.Date.valueOf(datemodif.getValue());
        Integer id_users = 2;
        Evenement evenement = new Evenement( nomE, dateE, lieuE, nbrPalace);
        serviceEvenement.update(i, evenement);
    }


    private void modifierr(Evenement evenement) {

       // LocalDate localDate1 = evenement.getDate()z;
       // datemodif.setValue(localDate1);

        lieu_modif.setText(evenement.getLieu());
        nom_modif.setText(evenement.getNom());
        i=evenement.getId();
    }

    public void showEventTable(){
        ObservableList<Evenement> list = getEvenList();
        nom_event.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom"));
        date_event.setCellValueFactory(new PropertyValueFactory<Evenement, String>("date"));
        lieu_event.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieu"));
        table_evenement.setItems(list);
    }
    private ObservableList<Evenement> getEvenList() {
        ObservableList<Evenement> EventListe = FXCollections.observableArrayList();
        DataSource ds = DataSource.getInstance();
        Connection cnx = ds.getCnx();
        ServiceEvenement serviceEvenement = new ServiceEvenement(cnx);
          EventListe = serviceEvenement.readAll();


        return EventListe;
    }

    private void supprimerr(Evenement evenement) {
        DataSource ds = DataSource.getInstance();
        Connection cnx = ds.getCnx();
    ServiceEvenement evenement3 = new ServiceEvenement(cnx);
        evenement3.delete(evenement.getId());

    }

    private void addListenerEvent() {

        table_evenement.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
               btnsupprimer.setDisable(false);
               btnmodif.setDisable(false);
                nom_event.setText(String.valueOf(newSelection.getNom()));
                lieu_event.setText(String.valueOf(newSelection.getLieu()));
                date_event.setText(String.valueOf(newSelection.getDate()));
            } else {
                btnsupprimer.setDisable(true);
                btnmodif.setDisable(true);
                date_event.setText("");
                date_event.setText(String.valueOf(newSelection.getDate()));
                nom_event.setText("");
                nom_event.setText(String.valueOf(newSelection.getNom()));
                lieu_event.setText("");
                lieu_event.setText(String.valueOf(newSelection.getLieu()));

            }
        });

    }


}
 */
package pages;

import entities.Produit;
import entities.categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import services.categorie_Service;
import services.produit_service;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;
import services.*;
import entities.*;
public class GererProduitController implements Initializable {

    ObservableList<Produit> list = FXCollections.observableArrayList();

    produit_service produitService = new produit_service();
    @FXML
    private VBox chosenProduit;

    @FXML
    private TableColumn<Produit, String> desc_produit;
    @FXML
    private TableColumn<Produit, Integer> id_produit;

    @FXML
    private TextField id_produit_modif;
    @FXML
    private TextField desc_produit_modif;

    @FXML
    private TableColumn<Produit, Integer> nbr_produit;

    @FXML
    private TextField nbr_produit_modif;

    @FXML
    private TableColumn<Produit, String> nom_produit;

    @FXML
    private TextField nom_produit_modif;

    @FXML
    private TableColumn<Produit, Integer> prix_produit;

    @FXML
    private TextField prix_produit_modif;

    @FXML
    private TableView<Produit> table_produit;

    @FXML
    private ComboBox<String> catemodif;
    @FXML
    private TableColumn<Produit, String> cat;

    @FXML
    private TableColumn<Produit, String> img;
    @FXML
    void btnimg(ActionEvent event) {

    }
    @FXML
    void Modif(ActionEvent event) {
        String nomProduit = nom_produit_modif.getText();
        String descProduit = desc_produit_modif.getText();
        int prixProduit = Integer.parseInt(prix_produit_modif.getText());
        int nbrProduit = Integer.parseInt(nbr_produit_modif.getText());
        int IDProduit = Integer.parseInt(id_produit_modif.getText());
        String categorie = catemodif.getValue();
        String image = img.getText();
        System.out.println(IDProduit);
        produit_service produitService = new produit_service();
        Produit produit = new Produit(IDProduit, nomProduit, descProduit, prixProduit, nbrProduit,categorie,image);
        System.out.println(produit);
        produitService.update(produit);
        showEventTable();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        for (int i = 0; i < produitService.readAll().size(); i++) {

            list.addAll(produitService.readAll().get(i));
        }
        categorie_Service service = new categorie_Service();
        ObservableList<categorie> listC = FXCollections.observableArrayList();
        listC = service.readAll();
        for (int i = 0; i < listC.size(); i++){
            catemodif.getItems().add(listC.get(i).getNom());
        }
        addListenerEvent();
        showEventTable();
    }

    private void addListenerEvent() {

        table_produit.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                id_produit_modif.setText(String.valueOf(newSelection.getId()));
                nom_produit_modif.setText(String.valueOf(newSelection.getNom_prod()));
                desc_produit_modif.setText(String.valueOf(newSelection.getDescription()));
                prix_produit_modif.setText(String.valueOf(newSelection.getPrix()));
                nbr_produit_modif.setText(String.valueOf(newSelection.getNbr_prods()));
                catemodif.setValue(String.valueOf(newSelection.getCategorie()));
            } else {
                id_produit_modif.setText("");
                id_produit_modif.setText(String.valueOf(newSelection.getId()));
                nom_produit_modif.setText("");
                nom_produit_modif.setText(String.valueOf(newSelection.getNom_prod()));
                desc_produit_modif.setText("");
                desc_produit_modif.setText(String.valueOf(newSelection.getDescription()));
                prix_produit_modif.setText("");
                prix_produit_modif.setText(String.valueOf(newSelection.getPrix()));
                nbr_produit_modif.setText("");
                nbr_produit_modif.setText(String.valueOf(newSelection.getNbr_prods()));
                catemodif.setValue(String.valueOf(newSelection.getCategorie()));

            }
        });

    }
    public void showEventTable(){
        ObservableList<Produit> list = getProduitList();
        id_produit.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
        nom_produit.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom_prod"));
        desc_produit.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        prix_produit.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("prix"));
        nbr_produit.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("nbr_prods"));
        cat.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));
        img.setCellValueFactory(new PropertyValueFactory<>("image"));
        img.setCellFactory(col -> new ImageTableCell<>());


        table_produit.setItems(list);
    }

    private ObservableList<Produit> getProduitList() {
        ObservableList<Produit> ProduitListe = FXCollections.observableArrayList();
        produit_service produitService = new produit_service();
        ProduitListe = produitService.readAll();
        return ProduitListe;
    }

    @FXML
    void delete(ActionEvent event) {
        int IDProduit = Integer.parseInt(id_produit_modif.getText());
        produit_service produitService = new produit_service();
        produitService.delete(IDProduit);
        showEventTable();
    }
   
}

