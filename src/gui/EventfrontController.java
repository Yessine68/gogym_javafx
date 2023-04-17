/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javafx.scene.web.WebEngine;
import Entities.CategorieEvenement;
import Entities.Evenement;
import Services.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EventfrontController implements Initializable {
    private List<Evenement> evenements = new ArrayList<>();
    private Listener1 listener1;
    @FXML
    private GridPane grid;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbdesasc;
    
     public  ObservableList<String> options = FXCollections.observableArrayList(
        "ASC", "DESC"
    );
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbdesasc.getSelectionModel().selectFirst();
        for (int i = 0; i < options.size(); i++){
        cbdesasc.getItems().add(options.get(i) );
       
       
       }
        
    
        int row = 0;

        Evenement e = new Evenement();
        EvenementService service = new EvenementService();
        evenements = service.readAll();

        if (evenements.size() > 0){
            listener1 = new Listener1(){
                public void onClickListener(Evenement evenement){
                    System.out.println(evenement);
                }

                @Override
                public void onClickListener(CategorieEvenement categorieEvenement) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
        }

        for (int i = 0; i < evenements.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventFrontItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventFrontItemController evenementitemController = fxmlLoader.getController();

                evenementitemController.setData(evenements.get(i));

                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(GestionevenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // TODO
    }    

     @FXML
    private void btnrecherce(ActionEvent event)  {
        try {
             int row = 0;
            String nom = tfrecherche.getText();
            EvenementService service = new EvenementService();
            evenements = (List<Evenement>) service.recherche(nom);
            grid.getChildren().clear();
            for (int i = 0; i < evenements.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventfrontItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
               
                EventFrontItemController eventFrontItemController = fxmlLoader.getController();
               

                eventFrontItemController.setData(evenements.get(i));
               
                grid.add(anchorPane,0 ,row++);
            }
           
            
           catch (IOException ex) {
                Logger.getLogger(       EventfrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            if (evenements != null) {
               
            } else {
                // handle case where Reservation is not found
            }
        } catch (NumberFormatException ex) {
           // handle case where nomRes is not a valid String
        }
    }
    
    
    @FXML
    private void btntrier(ActionEvent event) {
        
        
        		

        try {
             int row = 0;
            EvenementService service = new EvenementService();
            String ordre= cbdesasc.getValue();
            evenements=service.Trie(ordre, evenements);
            grid.getChildren().clear();
            for (int i = 0; i < evenements.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventfrontItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
               
                EventFrontItemController eventFrontItemController = fxmlLoader.getController();
               

                eventFrontItemController.setData(evenements.get(i));
               
                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(       EventfrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            if (evenements != null) {
               
            } else {
                // handle case where Reservation is not found
            }
        } catch (NumberFormatException ex) {
           // handle case where nomRes is not a valid String
        }
        
    }
    

    @FXML
   public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Ibrahimhome.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
    
}
