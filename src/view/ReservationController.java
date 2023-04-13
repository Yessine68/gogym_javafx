/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceRes;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField idr;
    @FXML
    private TextField idcour;
    @FXML
    private TextField type;
    @FXML
    private TextField date;
    @FXML
    private Button su;
    @FXML
    private Button aj;
    @FXML
    private Button mod;
    @FXML
    private TableView<Reservation> affiche;
    @FXML
    private TableColumn<Reservation, String> id;
    @FXML
    private TableColumn<Reservation, String> idc;
    @FXML
    private TableColumn<Reservation, String> dt;
    @FXML
    private TableColumn<Reservation, String> tp;

    /**
     * Initializes the controller class.
     */
    
    public void refreshTable() {
    ServiceRes us = new ServiceRes();
    List<Reservation> l = new ArrayList<>();
    l = (ArrayList<Reservation>) us.afficherRv();
    ObservableList<Reservation> data = FXCollections.observableArrayList(l);
    FilteredList<Reservation> fle = new FilteredList(data, e -> true);
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    idc.setCellValueFactory(new PropertyValueFactory<>("cours_id"));
    dt.setCellValueFactory(new PropertyValueFactory<>("date"));
    tp.setCellValueFactory(new PropertyValueFactory<>("type"));
    affiche.setItems(fle);
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       refreshTable();
        
    }    

    @FXML
    private void supp(ActionEvent event) throws IOException {
        
         ServiceRes sm = new ServiceRes() ;   

            

      StringBuilder errors=new StringBuilder();
        
        if(idr.getText().trim().isEmpty()){
            errors.append("Please enter an id\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     
                   sm.supprimerRv(Integer.parseInt(idr.getText()));

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("reservation is deleted successfully!");
            alert.show(); 
            
            refreshTable();
            
    }

    @FXML
    private void ajt(ActionEvent event) throws IOException, ParseException {
               
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    ServiceRes sm = new ServiceRes();
    Reservation c = new Reservation();
           
    StringBuilder errors = new StringBuilder();
        
    if(type.getText().trim().isEmpty() && date.getText().trim().isEmpty()){
        errors.append("svp entrer un type de cour et l'id de l'arbitre\n");
    }

    // Vérifier si le champ date est vide ou si la date saisie est incorrecte
    try {
        if(date.getText().trim().isEmpty()){
            errors.append("Please enter a date\n");
        } else {
            dateFormat.parse(date.getText());
        }
    } catch (ParseException e) {
        errors.append("Please enter a valid date (format: yyyy-MM-dd)\n");
    }
     
    if(errors.length() > 0){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(errors.toString());
        alert.showAndWait();
    } else {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("reservation is added successfully!");
        alert.show(); 

        c.setCours_id(Integer.parseInt(idcour.getText()));
      
        java.util.Date utilDate = dateFormat.parse(date.getText());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        c.setDate(sqlDate);
      
        c.setType(type.getText());

        sm.ajouterRv(c);
        refreshTable();
    }
}

    @FXML
    private void modifier(ActionEvent event) throws IOException, ParseException {
        
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Reservation c = new Reservation();
StringBuilder errors = new StringBuilder();

if (idr.getText().trim().isEmpty()) {
    errors.append("Please enter an id\n");
}

if (errors.length() > 0) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setContentText(errors.toString());
    alert.showAndWait();
}

ServiceRes sm = new ServiceRes();

c.setId(Integer.parseInt(idr.getText()));
c.setCours_id(Integer.parseInt(idcour.getText()));

java.util.Date utilDate = dateFormat.parse(date.getText());
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
c.setDate(sqlDate);

c.setType(type.getText());
sm.modiferRv(c);

refreshTable();
    }
        
        
        
        
        
        
        
        
    

    @FXML
    private void rtr(ActionEvent event) throws IOException{
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InterfaceCoursReservation.fxml"));
                Parent root = loader.load();
                InterfaceCoursReservationController aa = loader.getController();
                type.getScene().setRoot(root);
        
    }
    
    
}
