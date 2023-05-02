/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ServiceRes;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class ReservationController implements Initializable {

    @FXML
    private DatePicker reservationDatePicker;
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
    
            private ServiceRes rs =new ServiceRes();
    @FXML
    private Button statbutt;
    @FXML
    private TextField rec;
    @FXML
    private Button btnTrier;


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
List<Reservation> reservations = rs.afficherRv();
        Set<LocalDate> reservationDates = new HashSet<>();
        for (Reservation reservation : reservations) {
            LocalDate localDate = reservation.getDate().toLocalDate();
            reservationDates.add(localDate);
        }
        reservationDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (reservationDates.contains(date)) {
                        setText("Reserved");
                        setStyle("-fx-background-color: lightpink");
                    }
                }
            }
        });        
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
            
             Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Reservation supprimé avec succès").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
            
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
        
         Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Reservation ajouté avec succès").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
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

 Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Reservation modifié avec succès").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();

    }
        
        
        
        
        
        
        
        
    

    @FXML
    private void rtr(ActionEvent event) throws IOException{
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/InterfaceCoursReservation.fxml"));
                Parent root = loader.load();
                InterfaceCoursReservationController aa = loader.getController();
                type.getScene().setRoot(root);
        
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Statistics.fxml"));
                Parent root = loader.load();
                StatisticsController a = loader.getController();
                statbutt.getScene().setRoot(root);
    }

     @FXML
    private void search(ActionEvent event) throws IOException{
ServiceRes service = new ServiceRes();
List<Reservation> list = service.afficherRv();
ObservableList<Reservation> observableList = FXCollections.observableList(list);
ObservableList<Reservation> filteredList = FXCollections.observableArrayList();
         filteredList.clear();
        for (Reservation Skill : list) {
            if(String.valueOf(Skill.getDate()).contains(rec.getText())){
                filteredList.add(Skill);
            }
        }
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
    idc.setCellValueFactory(new PropertyValueFactory<>("cours_id"));
    dt.setCellValueFactory(new PropertyValueFactory<>("date"));
    tp.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        affiche.setItems(filteredList);
    }

    @FXML
    private void rec(KeyEvent event) {
    }

    @FXML
    private void affichrtTri(ActionEvent event) {
      List<Reservation> l = new ArrayList<>();
    l = (ArrayList<Reservation>) rs.afficherRv();
    ObservableList<Reservation> data = FXCollections.observableArrayList(l);
    data.sort((r1, r2) -> r1.getDate().compareTo(r2.getDate()));
    FilteredList<Reservation> fle = new FilteredList(data, e -> true);
    affiche.setItems(fle);
}
    
    
}
