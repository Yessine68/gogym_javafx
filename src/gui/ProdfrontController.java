
package gui;

import entities.*;
import services.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ProdfrontController implements Initializable {
    
    private List<Produit> produit = new ArrayList<>();
    private Listener2 listener2;
    @FXML
    private GridPane grid;
    @FXML
    private TextField Search;
    @FXML
    private ComboBox<String> tri;
    @FXML
    private Label title;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int row = 0;

        Produit p = new Produit();
        produit_service service = new produit_service();
        produit = service.readAll();
        
        tri.getItems().add("ASC");      
        tri.getItems().add("DESC");          

        if (produit.size() > 0){
        }

        for (int i = 0; i < produit.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProdFrontItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProdFrontItemController prodtItemController = fxmlLoader.getController();

                prodtItemController.setData(produit.get(i));

                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // TODO
    }    
/*
    @FXML
   public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Ademhome.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
*/
    @FXML
    private void Search(MouseEvent event) {
           try {
             int row = 0;
            String nom = Search.getText();
            produit_service service = new produit_service();
            produit = (List<Produit>) service.recherche(nom);
            grid.getChildren().clear();
            for (int i = 0; i < produit.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProdFrontItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
               
               ProdFrontItemController prodtItemController = fxmlLoader.getController();
               

                prodtItemController.setData(produit.get(i));
               
                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(       GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            if (produit != null) {
               
            } else {
                // handle case where Reservation is not found
            }
        } catch (NumberFormatException ex) {
           // handle case where nomRes is not a valid String
        }
    }

    @FXML
    private void Trier(MouseEvent event) {
        
         try {
             int row = 0;
            produit_service service = new produit_service();
            String nom = Search.getText();
            produit=service.Trie(tri.getValue(), produit);
            grid.getChildren().clear();
            for (int i = 0; i < produit.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProdFrontItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
               
                ProdFrontItemController prodtItemController = fxmlLoader.getController();
               

                prodtItemController.setData(produit.get(i));
               
                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(       GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            if (produit != null) {
               
            } else {
                // handle case where Reservation is not found
            }
        } catch (NumberFormatException ex) {
           // handle case where nomRes is not a valid String
        }
        
    }

    @FXML
    private void mppr(MouseEvent event) throws SQLException {
        try {
            title.setText("Meilleurs Produits");
            int row = 0;
            produit_service service = new produit_service();
            produit=service.getProdsbyRating();
            grid.getChildren().clear();
            for (int i = 0; i < produit.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProdFrontItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
               
                ProdFrontItemController prodtItemController = fxmlLoader.getController();
               

                prodtItemController.setData(produit.get(i));
               
                grid.add(anchorPane,0 ,row++);
                } catch (IOException ex) {
                    Logger.getLogger(       GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (produit != null) {
               
            } else {
                // handle case where Reservation is not found
            }
        } catch (NumberFormatException ex) {
           // handle case where nomRes is not a valid String
        }
    }

       @FXML
    private void profile(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
        
    }

     @FXML
    private void event(ActionEvent event) throws IOException {

        Parent root1 = FXMLLoader.load(getClass().getResource("/gui/Eventfront.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    private void produit(ActionEvent event) throws IOException {
         Parent root1 = FXMLLoader.load(getClass().getResource("/gui/Prodfront.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    private void abonnement(ActionEvent event) throws IOException {
         Parent root1 = FXMLLoader.load(getClass().getResource("/gui/Client/AbonnementClient.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    private void salle(ActionEvent event) throws IOException {
         Parent root1 = FXMLLoader.load(getClass().getResource("/gui/Client/SalleMenu.fxml"));
        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }

    
    
    
    
    
}
