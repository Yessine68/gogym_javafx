/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Cours;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceCr;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class AffCoursController implements Initializable {

   @FXML
    private TableView<Cours> affiche;
    @FXML
    private TableColumn<Cours, String> idcr;
    @FXML
    private TableColumn<Cours, String> nomcr;
    @FXML
    private TableColumn<Cours, String> dureecr;
    @FXML
    private TableColumn<Cours, String> intcr;
    @FXML
    private TableColumn<Cours, String> biencr;
    @FXML
    private TableColumn<Cours, String> imgcr;

    /**
     * Initializes the controller class.
     */
    
    
     public void refreshTable() {
    ServiceCr us = new ServiceCr();
    List<Cours> l = new ArrayList<>();
    l = (ArrayList<Cours>) us.afficherCr();
    ObservableList<Cours> data = FXCollections.observableArrayList(l);
    FilteredList<Cours> fle = new FilteredList(data, e -> true);
    idcr.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomcr.setCellValueFactory(new PropertyValueFactory<>("nom"));
    dureecr.setCellValueFactory(new PropertyValueFactory<>("duree"));
    intcr.setCellValueFactory(new PropertyValueFactory<>("it"));
    biencr.setCellValueFactory(new PropertyValueFactory<>("bienfait"));
    imgcr.setCellValueFactory(new PropertyValueFactory<>("image"));

    affiche.setItems(fle);
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }    
    
}
