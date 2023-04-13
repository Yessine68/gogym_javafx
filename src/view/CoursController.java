/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Cours;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceCr;




/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class CoursController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField it;
    @FXML
    private Button su;
    @FXML
    private Button aj;
    @FXML
    private Button mod;
    @FXML
    private TextField duree;   
    @FXML
    private TextField bien;
    @FXML
    private TextField img;
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
    intcr.setCellValueFactory(new PropertyValueFactory<>("intensite"));
    biencr.setCellValueFactory(new PropertyValueFactory<>("bienfaits"));
    imgcr.setCellValueFactory(new PropertyValueFactory<>("image"));

    affiche.setItems(fle);
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshTable();
    }    

    @FXML
    private void rtr(ActionEvent event)  throws IOException{
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InterfaceCoursReservation.fxml"));
                Parent root = loader.load();
                InterfaceCoursReservationController aa = loader.getController();
                nom.getScene().setRoot(root);
        
    }

    @FXML
    private void supp(ActionEvent event) throws IOException {
        
         ServiceCr sm = new ServiceCr() ;   

            

      StringBuilder errors=new StringBuilder();
        
        if(id.getText().trim().isEmpty()){
            errors.append("Please enter an id\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     
                   sm.supprimerCr(Integer.parseInt(id.getText()));

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours is deleted successfully!");
            alert.show(); 
            
            refreshTable();
    }

    @FXML
    private void ajt(ActionEvent event) throws IOException {
         ServiceCr sm = new ServiceCr() ;   
    Cours c = new Cours() ;
           
     
        StringBuilder errors=new StringBuilder();
        
        if(nom.getText().trim().isEmpty()&&duree.getText().trim().isEmpty()){
            errors.append("svp entrer un nom et la duree\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours is added successfully!");
            alert.show(); 
            
      
      c.setDuree(Integer.parseInt(duree.getText()));
      c.setNom(nom.getText());
      c.setIntensite(it.getText());
      c.setBienfaits(bien.getText());
      c.setImage(img.getText());

         
            sm.ajouterCr(c);
                       
            refreshTable();
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, ParseException {
        
    Cours c = new Cours();
    StringBuilder errors = new StringBuilder();
        
    if(id.getText().trim().isEmpty()){
        errors.append("Please enter an id\n");
    }
        
    // Vérifier si le champ nom est vide ou a une taille incorrecte
    if(nom.getText().trim().isEmpty() || nom.getText().length() < 3 || nom.getText().length() > 9){
        errors.append("Please enter a name between 4 and 9 characters\n");
    }
     
    if(errors.length() > 0){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(errors.toString());
        alert.showAndWait();
    } else { // Si la validation est réussie, effectuer la modification
        ServiceCr sm = new ServiceCr() ;  
         
        c.setId(Integer.parseInt(id.getText()));   
        c.setDuree(Integer.parseInt(duree.getText()));
        c.setNom(nom.getText());
        c.setIntensite(it.getText());
        c.setBienfaits(bien.getText());
        c.setImage(img.getText());
        
        sm.modiferCr(c);
        
        refreshTable();
    }
}

    
}
