/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import entities.Cours;
import entities.pdf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
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
    @FXML
    private TextField rec;
    @FXML
    private Button pdf;

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
        refreshTable();
    }    

    @FXML
private void search(ActionEvent event) throws IOException {
    ServiceCr service = new ServiceCr();
    List<Cours> list = service.afficherCr();
    ObservableList<Cours> observableList = FXCollections.observableList(list);
    ObservableList<Cours> filteredList = FXCollections.observableArrayList();
    filteredList.clear();
    for (Cours Skill : list) {
        if (String.valueOf(Skill.getNom()).contains(rec.getText()) ||
            (Skill.getIntensite()).contains(rec.getText())) {
            filteredList.add(Skill);
        }
    }
    idcr.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomcr.setCellValueFactory(new PropertyValueFactory<>("nom"));
    dureecr.setCellValueFactory(new PropertyValueFactory<>("duree"));
    intcr.setCellValueFactory(new PropertyValueFactory<>("intensite"));
    biencr.setCellValueFactory(new PropertyValueFactory<>("bienfaits"));
    imgcr.setCellValueFactory(new PropertyValueFactory<>("image"));

    affiche.setItems(filteredList);
}

    @FXML
    private void rec(KeyEvent event) {
    }

    @FXML
    private void pdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        pdf pd=new pdf();
        try{
        pd.GeneratePdf("liste des Cours");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceCr.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("cours.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
    
}
