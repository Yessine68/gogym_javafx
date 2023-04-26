/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Admin;

import GUI.Client.SalleMenuController;
import Entities.Abonnement;
import Entities.Salle;
import Services.AbonnementService;
import Services.SalleService;
import Utils.Variables;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapType;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MapViewEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierSalleController implements Initializable {

    @FXML
    private Button btnAjout;
    @FXML
    private TextField NomTf;
    @FXML
    private TextField EmailTf;
    @FXML
    private TextField TelTf;
    @FXML
    private Label NomL;
    @FXML
    private Label EmailL;
    @FXML
    private Label TelL;
    @FXML
    private Label AdresseL;
    @FXML
    private Label VilleL;
    @FXML
    private Label ImageL;
    @FXML
    private Label tel216L;
    @FXML
    private Label SalleL;
    @FXML
    private Label PerimetreL;
    @FXML
    private TextField VilleTf;
    @FXML
    private TextField PerimetreTf;
    @FXML
    private TextField AdresseTf;
    private TextField LikeTf;
    @FXML
    private Label m2L;
    @FXML
    private ImageView imageSalle;
    @FXML
    private Button buttontrash;
    @FXML
    private Text pathImage;
    private CheckBox comfortType;
    private CheckBox premiumType;
    private CheckBox allinType;
    @FXML
    private Label ImageL1;
    @FXML
    private Label ImageL2;
    @FXML
    private MapView mapView;
    @FXML
    private Label ImageL21;
    @FXML
    private Label ImageL3;
private boolean selectImage=false;
SalleService ss = new SalleService();
    @FXML
    private TextField longitude;
    @FXML
    private TextField latitude;
    Set<Marker> markers = new HashSet<>();
    @FXML
    private AnchorPane scrollPane;
    private List<String> abonnementNoms=new ArrayList<String>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            List<Abonnement> abonnementsSalle = ss.getAbonnementsSalle();
                    AbonnementService as = new AbonnementService();

            List<Abonnement> abonnements=as.recuperer();
            for (int i = 0;i<abonnements.size();i++){
                boolean abonnementExists = false;
                CheckBox check = new CheckBox();
                check.setText(abonnements.get(i).getNom_a());
                check.setLayoutX(7.0);
                check.setLayoutY(i*24);
                
                for (Abonnement abonnement : abonnementsSalle) {
                    if (abonnement.getId() == abonnements.get(i).getId()) {
                        abonnementExists = true;
                        abonnementNoms.add(abonnements.get(i).getNom_a());
                        break;
                    }
                }
                if (abonnementExists) {
                    check.setSelected(true);
                } else {
                                        check.setSelected(false);

                }
                scrollPane.getChildren().add(check);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        Salle salleC = Variables.salleDetail;
        NomTf.setText(salleC.getNom_s());
        EmailTf.setText(salleC.getEmail_s());
        TelTf.setText(Integer.toString(salleC.getTel_s()));
        AdresseTf.setText(salleC.getAdresse_s());
        VilleTf.setText(salleC.getVille_s());
        pathImage.setText(salleC.getImage_s());
        InputStream stream;
        try {
            stream = new FileInputStream("./src/Images/Salles/"+salleC.getImage_s());
            Image image = new Image(stream);
            imageSalle.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PerimetreTf.setText(Float.toString(salleC.getPerimetre_s()));
        String[] types = salleC.getType().split("/");
        mapView.initialize();
        mapView.initializedProperty().addListener((obs, oldVal, newVal) -> {
            mapView.setMapType(MapType.OSM);
            mapView.setZoom(10.0);
            File imageFile = new File(".\\src\\GUI\\blue_map_marker.png");
            if (salleC.getLatitude_s()!=0)   {
                mapView.setCenter(new Coordinate(salleC.getLatitude_s(),salleC.getLongitude_s()));
                try {
                    URL imageURL = imageFile.toURI().toURL();
                    Marker marker = new Marker(imageURL,-32,-57);
                    marker.setVisible(true);
                    marker.setPosition(new Coordinate(salleC.getLatitude_s(),salleC.getLongitude_s()));
                    mapView.addMarker(marker);
                    markers.add(marker);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(AddSalleController.class.getName()).log(Level.SEVERE, null, ex);
                }  
            } 
            else {
                mapView.setCenter(new Coordinate(34.73879126332555,10.757074356079102));
            }  
                mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {

                try {
                    URL imageURL = imageFile.toURI().toURL();
                    Marker marker = new Marker(imageURL,-32,-57);
                    marker.setVisible(true);
                    marker.setPosition(new Coordinate(event.getCoordinate().getLatitude(), event.getCoordinate().getLongitude()));
                    for (Marker m : markers) {
                        mapView.removeMarker(m);
                    }
                    markers.clear();
                    markers.add(marker);
                    mapView.addMarker(marker);
                    System.out.println(event.getCoordinate().getLatitude()+" "+event.getCoordinate().getLongitude());
                    longitude.setText(String.valueOf(event.getCoordinate().getLongitude()));
                    latitude.setText(String.valueOf(event.getCoordinate().getLatitude()));
                } catch (MalformedURLException ex) {
                    Logger.getLogger(AddSalleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });
        
        
    }    

   @FXML
    private void UpdateSalle(ActionEvent event) throws IOException {
        try {
            Salle selectedSalle = Variables.salleDetail;
            if (selectedSalle != null) {
                String nom_s = NomTf.getText();
                String email_s = EmailTf.getText();
                int tel_s = Integer.parseInt(TelTf.getText());
                String adresse_s = AdresseTf.getText();
                String ville_s = VilleTf.getText();
                String image_s = "";
                float perimetre_s = Float.parseFloat(PerimetreTf.getText());
                String type="";
                if(NomTf.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("nom manquant");
                    alert.setHeaderText("Veuillez saisir le nom de cette salle !");
                    alert.showAndWait();
                    return;
                }

                if(EmailTf.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("email manquant");
                    alert.setHeaderText("Veuillez saisir le mail de cette salle !");
                    alert.showAndWait();
                    return;
                } else if (!isValidEmail(EmailTf.getText())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("email invalide");
                    alert.setHeaderText("Veuillez saisir un mail valide !");
                    alert.showAndWait();
                    return;
                }

                if(TelTf.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("numéro de téléphone manquant");
                    alert.setHeaderText("Veuillez saisir le numéro de téléphone de cette salle !");
                    alert.showAndWait();
                    return;
                } else if (!isValidPhoneNumber(TelTf.getText())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("numéro de téléphone invalide");
                    alert.setHeaderText("Veuillez saisir un numéro de téléphone valide (8 chiffres) !");
                    alert.showAndWait();
                    return;
                }
                
                if (AdresseTf.getText().isEmpty() || VilleTf.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Adresse manquante");
                    alert.setHeaderText("Veuillez saisir l'adresse de cette salle !");
                    alert.showAndWait();
                    return;
                }
            
            /////////
                if (pathImage.getText().equals("")){
                    image_s= "no image.jpg";
                }
                else {
                    if (selectImage){
                        Random random = new Random();
                        int randomNum = random.nextInt(90000) + 10000;
                        image_s= "image"+randomNum+".jpg";
                        String image_s2= "image"+randomNum;
                        File outputfile = new File("./src/Images/Salles/"+image_s);
                        BufferedImage buffer = ImageIO.read(new File(pathImage.getText()));
                        ImageIO.write(buffer, "jpg",outputfile);
                    }
                    else image_s=selectedSalle.getImage_s();
                    
                }
            ////////
            double latitude_s=0;
            double longitude_s=0;
            if (!latitude.getText().equals("")){
                latitude_s=Double.parseDouble(latitude.getText());
                longitude_s=Double.parseDouble(longitude.getText());
            }
            Salle s = new Salle(nom_s, email_s, tel_s, adresse_s, ville_s, image_s, perimetre_s, Variables.salleDetail.getLike_s(),type,longitude_s,latitude_s);
            
                s.setId(selectedSalle.getId());
                
                List<String> abonnementsAdded = new ArrayList<String>();
                List<String> abonnementsDelete = new ArrayList<String>();

                for (Node node : scrollPane.getChildren()) {
                    if (node instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) node;
                        if (checkBox.isSelected() && !abonnementNoms.contains(checkBox.getText())){
                            abonnementsAdded.add(checkBox.getText());
                        }
                        else if (!checkBox.isSelected() && abonnementNoms.contains(checkBox.getText())){
                            abonnementsDelete.add(checkBox.getText());
                        }
                    // Add your code here to perform an action on the checkBox object
                    }
                }
                            ss.modifier(s,abonnementsAdded,abonnementsDelete);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifier réussi");
            alert.setHeaderText("La salle a été modifier avec succès.");
            alert.showAndWait();
                
                      selectImage=false;
                      Variables.salleDetail=null;
                                            Variables.allSalle = ss.recuperer(); 
                                            
                      Variables.stagee.close();

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aucune salle sélectionnée");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner une salle à modifier.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    public boolean isValidEmail(String email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        boolean isValid = false;
        String expression = "^[0-9]{8}$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private void Retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SalleMenu.fxml"));
            Parent root = loader.load();
            
            SalleMenuController controller = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void importImage(ActionEvent event) throws FileNotFoundException, SQLException {
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        String extension = null;
        if(selected !=null )
        {
            extension= selected.getAbsolutePath().substring(selected.getAbsolutePath().length()-3,selected.getAbsolutePath().length());
            System.out.println(extension);
            if(!extension.equals( "jpg") && !extension.equals("png"))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid picture");
                alert.setContentText("Invalid picture format (only jgp/png available)"); 
                alert.showAndWait();
            }
            else
            {
                pathImage.setText(selected.getAbsolutePath());
                InputStream stream = new FileInputStream(selected.getAbsolutePath());
                Image image = new Image(stream);
                imageSalle.setImage(image);
                buttontrash.setVisible(true);
                selectImage=true;
            }
            
        }
    }

    @FXML
    private void deleteImage(ActionEvent event) throws FileNotFoundException {
        pathImage.setText("");
        imageSalle.setImage(new Image(new FileInputStream("./src/Images/Salles/no image.jpg")));
    }
    
}
