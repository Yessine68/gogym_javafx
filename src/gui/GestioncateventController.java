/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.CategorieEvenement;
import Services.CategorieEvenementService;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class GestioncateventController implements Initializable {

    @FXML
    private GridPane grid;

   private List<CategorieEvenement> categories = new ArrayList<>();
   private Listener1 listener1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        int row = 0;

        CategorieEvenement c = new CategorieEvenement();
        CategorieEvenementService service = new CategorieEvenementService();
        categories = service.readAll();

        if (categories.size() > 0){
            listener1 = new Listener1(){
                @Override
                public void onClickListener(CategorieEvenement categorieEvenement){
                    System.out.println(categorieEvenement);
                }
            };
        }

        for (int i = 0; i < categories.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CategorieItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CategorieItemController categorieitemController = fxmlLoader.getController();

                categorieitemController.setData(categories.get(i),listener1);

                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(GestioncateventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    @FXML
    private void btnaddhome(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Ajoutereventcat.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

  
    
}
