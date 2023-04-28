/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Client;

import GUI.Admin.ModifierSalleController;
import Entities.Salle;
import Services.SalleService;
import Utils.Variables;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapType;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MapViewEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HanaM
 */
public class DetailSalleController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    private SalleService ss = new SalleService();
    @FXML
    private MapView mapView;
    @FXML
    private TextField longitude;
    @FXML
    private TextField latitude;
    @FXML
    private ImageView coeur1;
    @FXML
    private ImageView coeur0;
    /**
     * Initializes the controller class.
     */
    private int coeur = 0;
    @FXML
    private ImageView coeur11;
    @FXML
    private Text like;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        coeur1.setVisible(false);
        coeur0.setVisible(true);
        Salle salleClicked = Variables.salleDetail;
        InputStream stream2;
        try {
            stream2 = new FileInputStream("./src/Images/Salles/"+salleClicked.getImage_s());
            Image image2 = new Image(stream2);
            
            ((ImageView)anchorPane.getChildren().get(0)).setImage(image2);
            ((Text)anchorPane.getChildren().get(10)).setText(salleClicked.getNom_s());
               ((Text)anchorPane.getChildren().get(11)).setText(salleClicked.getEmail_s());
                ((Text)anchorPane.getChildren().get(12)).setText(String.valueOf(salleClicked.getTel_s()));
                ((Text)anchorPane.getChildren().get(13)).setText(salleClicked.getVille_s());
                ((Text)anchorPane.getChildren().get(14)).setText(salleClicked.getAdresse_s());
                ((Text)anchorPane.getChildren().get(15)).setText(String.valueOf(salleClicked.getPerimetre_s()));
                String[] types = salleClicked.getType().split("/");
                String type = "";
                for (int i=0;i<=types.length-1;i++){
                    System.out.println(types[i]);
                    type+=types[i]+"\n";
                }
                ((Text)anchorPane.getChildren().get(16)).setText(type);
                ((Text)anchorPane.getChildren().get(17)).setText(String.valueOf(salleClicked.getLike_s()));
                ((TextField)anchorPane.getChildren().get(18)).setText(String.valueOf(salleClicked.getLongitude_s()));
                ((TextField)anchorPane.getChildren().get(19)).setText(String.valueOf(salleClicked.getLatitude_s()));
                setMapView(salleClicked);
                    
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailSalleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DetailSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
    }    

    private void modifierSalle(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierSalle.fxml"));
            Parent root = loader.load();
            ModifierSalleController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void deleteSalle(ActionEvent event) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete this Salle?");
        alert.setContentText("This action cannot be undone.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ss.supprimer(Variables.salleDetail);
            Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            primaryStage.close(); 
            //primaryStage.setScene(new Scene(root));
        }
    }

    private void setMapView(Salle salleClicked) throws MalformedURLException {
        mapView.initialize();
        mapView.initializedProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal) {
            if (salleClicked.getLongitude_s() != 0){
                mapView.setCenter(new Coordinate(salleClicked.getLatitude_s(), salleClicked.getLongitude_s()));

            }
            else {
                mapView.setCenter(new Coordinate(34.73879126332555,10.757074356079102));

                
            }
            mapView.setMapType(MapType.OSM);
            mapView.setZoom(10.0);

            if (salleClicked.getLongitude_s() != 0) {
                File imageFile = new File(".\\src\\GUI\\blue_map_marker.png");
                URL imageURL;
                try {
                    imageURL = imageFile.toURI().toURL();
                
                    Marker marker = new Marker(imageURL, -32, -57);
                    marker.setVisible(true);
                    marker.setPosition(new Coordinate(salleClicked.getLatitude_s(), salleClicked.getLongitude_s()));
                    mapView.addMarker(marker);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(DetailSalleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
        
        
    }


        

    @FXML
    private void coeur1Clicked(MouseEvent event) throws SQLException {
            coeur1.setVisible(false);
            coeur0.setVisible(true);
            Variables.salleDetail.setLike_s(Variables.salleDetail.getLike_s()-1);
            ss.modifier(Variables.salleDetail);
            like.setText(String.valueOf(Integer.parseInt(like.getText())-1));
    }

    @FXML
    private void coeur0Clicked(MouseEvent event) throws SQLException {
            coeur0.setVisible(false);
            coeur1.setVisible(true);
            Variables.salleDetail.setLike_s(Variables.salleDetail.getLike_s()+1);
            ss.modifier(Variables.salleDetail);
            like.setText(String.valueOf(Integer.parseInt(like.getText())+1));
    }

    @FXML
    private void OpenAbonnements(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AbonnementsSalle.fxml"));
            Parent root = loader.load();
            AbonnementsSalleController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
