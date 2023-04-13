/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class GestionevenementController implements Initializable {

    @FXML
    private GridPane grid;
    
     private List<Evenement> evenements = new ArrayList<>();
    private Listener1 listener1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                fxmlLoader.setLocation(getClass().getResource("EvenementItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EvenementItemController evenementitemController = fxmlLoader.getController();

                evenementitemController.setData(evenements.get(i));

                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(GestionevenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    
       @FXML
    private void btnaddhome(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
   
}
