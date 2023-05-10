/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import services.CategorieEvenementService;
import services.EvenementService;
import static gui.ModifierCategorieEvenementController.categorie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EvenementItemController implements Initializable {

    @FXML
    private Label cat;
    @FXML
    private Label nomlabel;
    @FXML
    private Label descriptionlabel;
    @FXML
    private ImageView imgitem;
    @FXML
    private Label datelabel;
    @FXML
    private Label lieulabel;
    @FXML
    private Label nbrparticipantslabel;
    private Evenement evenement;
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setData(Evenement evenement) {
        this.evenement = evenement;
        cat.setText(evenement.getCategorieEvenement());
        nomlabel.setText(evenement.getNom_e());
        datelabel.setText(evenement.getDate_e());
        lieulabel.setText(evenement.getLieu_e());
        nbrparticipantslabel.setText(Integer.toString(evenement.getNbr_participants()));
        Image image = new Image("http://localhost:8000/uploads/evenement/"+evenement.getImage());
        imgitem.setImage(image);
       // Image image = new Image(getClass().getResourceAsStream("../uploads/" + evenement.getImage()));
       // imgitem.setImage(image);
    }

  /*  
     @FXML
    private void btnedit(MouseEvent event) throws IOException {
        ModifierUserController.u= u;
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("ModifierUser.fxml"));
            Scene tabbleViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tabbleViewScene);
            window.show();
    }
    */
  @FXML
  
    private void delbtn(ActionEvent event) throws IOException {
        EvenementService service = new EvenementService();
        
        // Confirmation de la suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette categorie ?");
        alert.showAndWait();
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Gestionevenement.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
        service.delete(evenement);
    }
    
    
      @FXML
    private void btnclicked(ActionEvent event) throws IOException {
        ModifierEvenementController.evenement = evenement;
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
  /*
    @FXML
    private void btndel(MouseEvent event) throws IOException {
        ServiceUser service = new ServiceUser();
        service.delete(u);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionUser.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
        
    }
    
    */
    
}
