
package gui;

import entities.*;
import services.*;
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


public class GestionProduitController implements Initializable {

    @FXML
    private GridPane grid;
    
     private List<Produit> Produits = new ArrayList<>();
    private Listener2 listener2;

    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        int row = 0;

        Produit e = new Produit();
        produit_service service = new produit_service();
       Produits = service.readAll();

        if (Produits.size() > 0){
        }

        for (int i = 0; i <Produits.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProdItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProduitItemController evenementitemController = fxmlLoader.getController();

                evenementitemController.setData(Produits.get(i));

                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    
       @FXML
    private void btnaddhome(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
   
    
    @FXML
     public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Ademhome.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

    @FXML
    private void Stat(ActionEvent event)throws IOException {
      Parent tableViewParent = FXMLLoader.load(getClass().getResource("StatProd.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();  
    }
    
    
    
}
