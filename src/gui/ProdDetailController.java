
package gui;

import entities.*;
import services.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Tools.*;
import com.twilio.*;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.sql.SQLException;
import static java.time.Clock.system;
import java.time.LocalDate;
import javafx.scene.control.Button;
import org.controlsfx.control.Rating;

public class ProdDetailController implements Initializable {

    @FXML
    private Label namefxid;
    @FXML
    private ImageView imagefxid;
    @FXML
    private Label Quantitefxid;
    @FXML
    private Label Prix;
    @FXML
    private Label descriptionfxid;
    
 
    @FXML
    private ImageView qrView;
    
    public static Produit produit;
    @FXML
    private Label errorfxid;
    @FXML
    private Rating rating;
    @FXML
    private Button ratebtn;
    
    public static final String ACCOUNT_SID = "AC245b5c5bd09bb2cf9bf3e04afa90b1a1";
    public static final String AUTH_TOKEN = "540eb87a7c81f1b7c83bfdfcdf8a6327";
    public static final String TWILIO_NUMBER = "+16206341718";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            namefxid.setText(produit.getNom_prod());
            descriptionfxid.setText(produit.getDescription());
            Prix.setText(String.valueOf(produit.getPrix()));
            Quantitefxid.setText(String.valueOf(produit.getNbr_prods()));
            Image image = new Image(getClass().getResourceAsStream("../uploads/"+produit.getImage()));
            imagefxid.setImage(image);
            int id_user = 2;
            produit_service service = new produit_service();
            int checkrate = service.hasUserRatedProduct(produit.getId(),id_user);
            System.out.println(checkrate);
            if(checkrate != 0){
            rating.setRating(checkrate);
            rating.setDisable(true);
            ratebtn.setDisable(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    

  
    
    @FXML
      public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Prodfront.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

    @FXML
    private void rate(ActionEvent event) throws SQLException {
        System.out.println("rating donner par user: " +rating.getRating());
        int id_user = 2;
        produit_service service = new produit_service();
        service.addRating(produit.getId(),id_user,(int) rating.getRating());
        rating.setDisable(true);
        ratebtn.setDisable(true);
        
        
            String toPhoneNumber = "+21629808180";

        
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);

    String messageText = ""
            + "Merci "+ "Adem Mzid "+" pour votre avis de" + String.valueOf(rating.getRating()) +"  Stars"
            +"GoGym";
    Message message = Message.creator(new PhoneNumber(toPhoneNumber),
            new PhoneNumber(TWILIO_NUMBER),
            messageText).create();


        
    }
    
}
