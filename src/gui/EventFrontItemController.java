/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Evenement;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EventFrontItemController implements Initializable {

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
        // TODO
    }    
    
    
     public void setData(Evenement evenement) {
        this.evenement = evenement;
        cat.setText(evenement.getCategorieEvenement());
        nomlabel.setText(evenement.getNom_e());
        datelabel.setText(evenement.getDate_e());
        lieulabel.setText(evenement.getLieu_e());
        nbrparticipantslabel.setText(Integer.toString(evenement.getNbr_participants()));
        Image image = new Image(getClass().getResourceAsStream("../uploads/"+evenement.getImage()));
        imgitem.setImage(image);
       // Image image = new Image(getClass().getResourceAsStream("../uploads/" + evenement.getImage()));
       // imgitem.setImage(image);
    }

    
     @FXML
    private void btnDetail(ActionEvent event) throws IOException {
        EventDetailController.evenement = evenement;
        //System.out.println(EventDetailController.evenement);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EventDetail.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
}
