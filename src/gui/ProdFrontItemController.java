
package gui;

import entities.*;
import services.*;
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


public class ProdFrontItemController implements Initializable {

    @FXML
    private Label cat;
    @FXML
    private Label nomlabel;
    @FXML
    private Label descriptionlabel;
    @FXML
    private ImageView imgitem;
    
    @FXML
    private Label Prix;
    @FXML
    private Label Quantite;
    private Produit prod;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    
     public void setData(Produit prod) {
        this.prod = prod;
        cat.setText(prod.getCategorie());
        nomlabel.setText(prod.getNom_prod());
        descriptionlabel.setText(prod.getDescription());
        Prix.setText(Integer.toString(prod.getPrix()));
        Quantite.setText(Integer.toString(prod.getNbr_prods()));
        Image image = new Image("http://localhost:8000/uploads/produit/"+prod.getImage());
        imgitem.setImage(image);
       
    }

    
     @FXML
    private void btnDetail(ActionEvent event) throws IOException {
        ProdDetailController.produit = prod;
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ProdDetail.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
    

