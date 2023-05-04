/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import entities.CategorieEvenement;
import entities.Evenement;
import services.EvenementService;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EventfrontController implements Initializable {
    
    
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
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
  
   String[] words = {"ghazela", "ariana", "menzah", "nasser"};
   Set<String> possibleWordSet = new HashSet<>();
    AutoCompletionBinding<String> autoCompletionBinding;
    @FXML
    private MediaView mediaView;
    @FXML
    private AnchorPane mainpane;
   // @FXML
    //private Button btnRetour;
    @FXML
    private Button btnChercher;
    @FXML
    private Button btnTrier;
    @FXML
    private ImageView An;
    @FXML
    private ImageView Fr;
    public static String Langue = "Fr";
    @FXML
    private Label Title;
  
    @FXML
    private ImageView idpause;
    @FXML
    private ImageView idplay;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         file = new File("C:\\Users\\don7a\\Desktop\\music1.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        //mediaPlayer.setMute(true);
        mediaView.setMediaPlayer(mediaPlayer);
        
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
    
    
     private void learnWord(String text) {
        possibleWordSet.add(text);
        if (autoCompletionBinding != null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(tfrecherche, possibleWordSet);

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
    private void chatbot(MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show(); 
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
    private void pause(MouseEvent event) {
         mediaPlayer.pause();
    }
    @FXML
     private void play(MouseEvent event) {
         mediaPlayer.play();

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
                //btnRetour.setText("Retour");
                btnChercher.setText("Rechercher");
                btnTrier.setText("Trier");
                Title.setText("Liste des Evenements:");
               

                 
            
        }
        else {
                //btnRetour.setText("Back");
                btnChercher.setText("Search");
                btnTrier.setText("Sort");
                Title.setText("Events list:");
                
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
