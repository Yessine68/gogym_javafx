/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Evenement;
import Services.EvenementService;
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
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class EventDetailController implements Initializable {

    @FXML
    private Label namefxid;
    @FXML
    private ImageView imagefxid;
    @FXML
    private Label nbrfxid;
    @FXML
    private Label lieufxid;
    @FXML
    private Label descriptionfxid;
    @FXML
    private Label datefxid;
    //@FXML
    //private Label errorfxid;
    @FXML
    private ImageView qrView;
 
     public static Evenement evenement;
     public int Eventid;
    Random rand = new Random();
    int randomcode = rand.nextInt(9999);
    String code = String.valueOf(randomcode);

    String usernom = "ibrahim"; 
    int userid = 1 ;
    @FXML
    private Label errorfxid;
    @FXML
    private Button btnparticiper;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        namefxid.setText(evenement.getNom_e());
        descriptionfxid.setText(evenement.getDescription_e());
        datefxid.setText(evenement.getDate_e());
        lieufxid.setText(evenement.getLieu_e());
        nbrfxid.setText(String.valueOf(evenement.getNbr_participants()));
        Image image = new Image(getClass().getResourceAsStream("../uploads/"+evenement.getImage()));
        imagefxid.setImage(image);
              
        if (evenement.getNbr_participants() == 0) {
        btnparticiper.setVisible(false);
            Alert alertType = new Alert(Alert.AlertType.ERROR);
            alertType.setTitle("Error");
            alertType.setHeaderText("L'événement est complet");
            alertType.show();
            return;
        }
 
    }    
    
    
   
    
    
       @FXML
    private void participer(ActionEvent event) throws Exception {
         EvenementService ths = new EvenementService();
         Evenement e = evenement;
        JavaMailEvent mail = new JavaMailEvent();
        System.out.println(e);
        
        
        if (ths.check(e.getId(), userid) == true) {
             Alert alertType=new Alert(Alert.AlertType.ERROR);
        alertType.setTitle("Error");
        alertType.setHeaderText("vous avez deja participé");
        alertType.show();
        return;
        //    errorfxid.setText("vous avez deja participé");
        } 
        

        
        
        else {
            ths.Participer(e, code,userid);
            ths.decrement(e);
            nbrfxid.setText(String.valueOf(evenement.getNbr_participants()-1));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");   
            alert.setHeaderText(null);
            alert.setContentText("Bonjour mr/mme " + usernom + "Merci pour votre participation et voici votre pass pour l'evenement" +"on vous attend chaleurheusement !");
            alert.showAndWait();
            
            
          /*  String content;
             content = "Bonjour mr/mme " + usernom + "\n"
                     + "Merci pour votre participation et voici votre pass pour l'evenement\n"
                     + "on vous attend chaleurheusement ! ";*/
           mail.sendmail("Confirmation de participation!","ibrahim.souissi@esprit.tn");
qr();
        }
       //  EvenementService ths = new EvenementService();
        // Evenement e = ths.EventDetailFront(Eventid);
        //JavaMailEvent mail = new JavaMailEvent();
       // mail.sendmail("Confirmation de participation!","ibrahim.souissi@esprit.tn");
        //qr();
    }
    
    
    public void qr(){
      Evenement e = evenement;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "bonjour" + usernom + "votre code de participation a l'evenement:" +e.getNom_e()+ "est" +code+ "."   ;
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //ImageView qrView = new ImageView();
        
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
      
        }
    
    
    
    
    
    
  
    
    @FXML
      public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Eventfront.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }



    
    
}
