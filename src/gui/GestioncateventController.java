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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

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
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnrecherche;
    @FXML
    private ImageView An;
    @FXML
    private ImageView Fr;
     public static String Langue = "Fr";
     
     String[] words = {"categorie", "categorie2", "categorie3", "categorie4"};
    Set<String> possibleWordSet = new HashSet<>();
    AutoCompletionBinding<String> autoCompletionBinding;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
              Collections.addAll(possibleWordSet, words);
        autoCompletionBinding = TextFields.bindAutoCompletion(tfrecherche, possibleWordSet);

        tfrecherche.setOnKeyPressed(
                (KeyEvent e) -> {
                    switch (e.getCode()) {
                        case ENTER:
                            learnWord((tfrecherche.getText()));
                            break;
                        default:
                            break;
                    }
                }
        );
        
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
    private void btnrecherce(ActionEvent event)  {
        try {
             int row = 0;
            String nom = tfrecherche.getText();
            CategorieEvenementService service = new CategorieEvenementService();
            categories = (List<CategorieEvenement>) service.recherche(nom);
            grid.getChildren().clear();
            for (int i = 0; i < categories.size(); i++){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CategorieItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
               
                CategorieItemController categorieitemController = fxmlLoader.getController();
               

                 categorieitemController.setData(categories.get(i),listener1);
               
                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(       GestioncateventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            if (categories != null) {
               
            } else {
                // handle case where Reservation is not found
            }
        } catch (NumberFormatException ex) {
           // handle case where nomRes is not a valid String
        }
    }
    
    
    
     private void learnWord(String text) {
        possibleWordSet.add(text);
        if (autoCompletionBinding != null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(tfrecherche, possibleWordSet);

    }
    
    
    
    
    @FXML
    private void btnaddhome(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Ajoutereventcat.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

    @FXML
   public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Ibrahimhome.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

    @FXML
    private void langueAng(MouseEvent event) {
        this.Langue="Ang";
        Traduction();
    }
    

    @FXML
    private void langueFr(MouseEvent event) {
        this.Langue="Fr";
        Traduction();
    }
       public void Traduction(){
        if ("Fr".equals(this.Langue))
        {
                btnajout.setText("Ajouter");
                btnretour.setText("Retour");
                btnrecherche.setText("Rechercher");
                
               

                 
            
        }
        else {
                btnajout.setText("Add");
                btnretour.setText("Back");
                btnrecherche.setText("Search");
                
                
        }
    }

    
    
    
}

