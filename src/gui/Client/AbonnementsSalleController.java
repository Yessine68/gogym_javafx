/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Client;

import entities.Abonnement;
import services.AbonnementService;
import utils.Variables;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HanaM
 */
public class AbonnementsSalleController implements Initializable {

    @FXML
    private TableView<Abonnement> AbonnementTv;
    @FXML
    private TableColumn<Abonnement, String> NomC;
    @FXML
    private TableColumn<Abonnement, String> TypeC;
    @FXML
    private TableColumn<Abonnement, Integer> PrixC;
    @FXML
    private TableColumn<Abonnement, String> DescriptionC;
    @FXML
    private TableColumn<Abonnement, Date> DebutC;
    @FXML
    private TableColumn<Abonnement, Date> FinC;
    AbonnementService as = new AbonnementService();

    ObservableList<Abonnement> obs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Abonnement> abonnements;
        try {
            abonnements = as.abonnementsSalle(Variables.salleDetail);
            obs = FXCollections.observableArrayList(abonnements); //Observablelist il peut detecter les changement
        AbonnementTv.setItems(obs);
        NomC.setCellValueFactory(new PropertyValueFactory<>("nom_a"));
        TypeC.setCellValueFactory(new PropertyValueFactory<>("type_a"));
        PrixC.setCellValueFactory(new PropertyValueFactory<>("prix_a"));
        DescriptionC.setCellValueFactory(new PropertyValueFactory<>("description_a"));
        DebutC.setCellValueFactory(new PropertyValueFactory<>("debut_a"));
        FinC.setCellValueFactory(new PropertyValueFactory<>("fin_a"));
        } catch (SQLException ex) {
            Logger.getLogger(AbonnementsSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
