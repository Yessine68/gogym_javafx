/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CategorieEvenement;
import entities.Evenement;
import services.EvenementService;
import services.PDFevenement;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class GestionevenementController implements Initializable {

    @FXML
    private GridPane grid;
    
     private List<Evenement> evenements = new ArrayList<>();
    private Listener1 listener1;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ComboBox<String> cbdesasc;
      public  ObservableList<String> options = FXCollections.observableArrayList(
        "ASC", "DESC"
              );
    @FXML
    private AnchorPane mainpane;
    @FXML
    private Label titlefxid;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button btnajout;
    //@FXML
   // private Button btnretour;
    @FXML
    private Button btnrecherche;
    @FXML
    private Button btntrier;
    String[] words = {"ghazela", "ariana", "menzah", "nasser"};
    Set<String> possibleWordSet = new HashSet<>();
    AutoCompletionBinding<String> autoCompletionBinding;
    @FXML
    private ImageView An;
    @FXML
    private ImageView Fr;
    public static String Langue = "Fr";

  
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
                fxmlLoader.setLocation(getClass().getResource("EvenementItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
               
                EvenementItemController evenementItemController = fxmlLoader.getController();
               

                evenementItemController.setData(evenements.get(i));
               
                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(       GestionevenementController.class.getName()).log(Level.SEVERE, null, ex);
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
                fxmlLoader.setLocation(getClass().getResource("EvenementItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
               
                EvenementItemController evenementItemController = fxmlLoader.getController();
               

                evenementItemController.setData(evenements.get(i));
               
                grid.add(anchorPane,0 ,row++);
            } catch (IOException ex) {
                Logger.getLogger(       GestionevenementController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void PDFevenement(MouseEvent event) throws DocumentException, BadElementException, IOException {
         PDFevenement p = new PDFevenement();
            p.liste_SallePDF("Mes evenements");
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

        Parent root = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
   
    /*
    @FXML
     public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Ibrahimhome.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
*/

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
               // btnretour.setText("Retour");
                btnrecherche.setText("Rechercher");
                btntrier.setText("Trier");
                titlefxid.setText("Gestion Evenement:");
               

                 
            
        }
        else {
                btnajout.setText("Add");
              //  btnretour.setText("Back");
                btnrecherche.setText("Search");
                btntrier.setText("Sort");
                titlefxid.setText("Event Manegement:");
                
        }
    }

   
    
    
}
