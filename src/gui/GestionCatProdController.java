
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class GestionCatProdController implements Initializable {

    @FXML
    private GridPane grid;

   private List<categorie> categories = new ArrayList<>();
   private Listener1 listener1;

    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        int row = 0;

        categorie c = new categorie();
        categorie_Service service = new categorie_Service();
        categories = service.readAll();

        if (categories.size() > 0){

        }

        for (int i = 0; i < categories.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CategorieProdItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CategorieProdItemController categorieitemController = fxmlLoader.getController();

                categorieitemController.setData(categories.get(i),listener1);

                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(GestionCatProdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    @FXML
    private void btnaddhome(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjouterProdCat.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

   public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Ademhome.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
    
    
    
}
