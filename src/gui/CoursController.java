/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Cours;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCr;




/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class CoursController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField it;
    @FXML
    private Button su;
    @FXML
    private Button aj;
    @FXML
    private Button mod;
    @FXML
    private TextField duree;   
    @FXML
    private TextField bien;
    @FXML
    private TextField img;
    @FXML
    private TableView<Cours> affiche;
    @FXML
    private TableColumn<Cours, String> idcr;
    @FXML
    private TableColumn<Cours, String> nomcr;
    @FXML
    private TableColumn<Cours, String> dureecr;
    @FXML
    private TableColumn<Cours, String> intcr;
    @FXML
    private TableColumn<Cours, String> biencr;
    @FXML
    private TableColumn<Cours, String> imgcr;
    @FXML
    private Button AddImage;
    @FXML
    private ImageView Image;
    @FXML
    private TextField rec;
    @FXML
    private Button btnTrier;
    
    
    

    /**
     * Initializes the controller class.
     */
    
    
   public void refreshTable() {
    ServiceCr us = new ServiceCr();
    List<Cours> l = new ArrayList<>();
    l = (ArrayList<Cours>) us.afficherCr();
    ObservableList<Cours> data = FXCollections.observableArrayList(l);
    FilteredList<Cours> fle = new FilteredList(data, e -> true);
    idcr.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomcr.setCellValueFactory(new PropertyValueFactory<>("nom"));
    dureecr.setCellValueFactory(new PropertyValueFactory<>("duree"));
    intcr.setCellValueFactory(new PropertyValueFactory<>("intensite"));
    biencr.setCellValueFactory(new PropertyValueFactory<>("bienfaits"));
    imgcr.setCellValueFactory(new PropertyValueFactory<>("image"));

    /*imgcr.setCellFactory(param -> new TableCell<Cours, String>() {
        private final ImageView imageView = new ImageView();
        @Override
        protected void updateItem(String imagePath, boolean empty) {
            super.updateItem(imagePath, empty);
            if (empty || imagePath == null) {
                setGraphic(null);
            } else {
                try {
                    Image image = new Image(new FileInputStream(imagePath));
                    imageView.setImage(image);
                    setGraphic(imageView);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    });*/

    affiche.setItems(fle);
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshTable();
    }    

    @FXML
    private void rtr(ActionEvent event)  throws IOException{
        
          Parent root = FXMLLoader.load(getClass().getResource("/gui/reservation.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
        
    }

    @FXML
    private void supp(ActionEvent event) throws IOException {
        
         ServiceCr sm = new ServiceCr() ;   

            

      StringBuilder errors=new StringBuilder();
        
        if(id.getText().trim().isEmpty()){
            errors.append("Please enter an id\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     
                   sm.supprimerCr(Integer.parseInt(id.getText()));

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours is deleted successfully!");
            alert.show(); 
            
            refreshTable();
             Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Cours supprimé avec succès").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
             
    }

    @FXML
    private void ajt(ActionEvent event) throws IOException {
         ServiceCr sm = new ServiceCr() ;   
    Cours c = new Cours() ;
           
     
        StringBuilder errors=new StringBuilder();
        
        if(nom.getText().trim().isEmpty()&&duree.getText().trim().isEmpty()){
            errors.append("Please enter a name and duree\n");
        }
        
         if(nom.getText().trim().isEmpty() || nom.getText().length() < 3 || nom.getText().length() > 9){
        errors.append("Please enter a name between 4 and 9 characters\n");
    }
         
          if(it.getText().trim().isEmpty() || it.getText().length() < 3 || it.getText().length() > 14){
        errors.append("Please enter a intensite between 4 and 14 characters\n");
    }
          
          
         
       if(duree.getText().trim().isEmpty()) {
        errors.append("Please enter a duree.\n");
    } else {
        try {
            int dureeInt = Integer.parseInt(duree.getText());
            if (dureeInt < 10 || dureeInt > 99) {
                errors.append("Duree must be a 2-digit positive integer.\n");
            }
        } catch (NumberFormatException e) {
            errors.append("The duree number must be an 2 integer.\n");
        }
    }
        
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
     
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours is added successfully!");
            alert.show(); 
            
      
      c.setDuree(Integer.parseInt(duree.getText()));
      c.setNom(nom.getText());
      c.setIntensite(it.getText());
      c.setBienfaits(bien.getText());
      c.setImage(img.getText());

         
            sm.ajouterCr(c);
                       
            refreshTable();
            
            duree.setText("");
        nom.setText("");
        it.setText("");
        bien.setText("");
        img.setText("");
        
        Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Cours ajouté avec succès").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        
    }
    }
    @FXML
    private void modifier(ActionEvent event) throws IOException, ParseException {
        
    Cours c = new Cours();
    StringBuilder errors = new StringBuilder();
        
    if(id.getText().trim().isEmpty()){
        errors.append("Please enter an id\n");
    }
        
    // Vérifier si le champ nom est vide ou a une taille incorrecte
    if(nom.getText().trim().isEmpty() || nom.getText().length() < 3 || nom.getText().length() > 9){
        errors.append("Please enter a name between 4 and 9 characters\n");
    }
     
    if(errors.length() > 0){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(errors.toString());
        alert.showAndWait();
    } else { // Si la validation est réussie, effectuer la modification
        ServiceCr sm = new ServiceCr() ;  
         
        c.setId(Integer.parseInt(id.getText()));   
        c.setDuree(Integer.parseInt(duree.getText()));
        c.setNom(nom.getText());
        c.setIntensite(it.getText());
        c.setBienfaits(bien.getText());
        c.setImage(img.getText());
        
        sm.modiferCr(c);
        
        refreshTable();
        
        id.setText("");
        duree.setText("");
        nom.setText("");
        it.setText("");
        bien.setText("");
        img.setText("");
        
         Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Cours modifié avec succès").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
}

    @FXML
    private void AddImage(ActionEvent event) throws FileNotFoundException, IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.PNG", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        //String DBPath = "C:\\\\xampp\\\\htdocs\\\\Version-Integre\\\\public\\\\uploads\\\\" + x + ".jpg";
                String DBPath = "" + x + ".jpg";

     if (file != null) {
    FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
    FileOutputStream Fdestination = new FileOutputStream(DBPath);
    BufferedInputStream bin = new BufferedInputStream(Fsource);
    BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
    System.out.println(file.getAbsoluteFile());
    String path = file.getAbsolutePath();
    String res;
    int len;
    len=path.length();
    String h ;
    if (len >= 47) {
        res = path.substring(0,len);
        System.out.println(res);
        h=res;
        img.setText(res);

    } else {
        res = path;
    }
    
    Image imgs = new Image(file.toURI().toString());
    Image.setImage(imgs);
    
    int b = 0;
    while (b != -1) {
        b = bin.read();
        bou.write(b);
    }
    bin.close();
    bou.close();
} else {
    System.out.println("error");
}


    
    }

   @FXML
private void search(ActionEvent event) throws IOException {
    ServiceCr service = new ServiceCr();
    List<Cours> list = service.afficherCr();
    ObservableList<Cours> observableList = FXCollections.observableList(list);
    ObservableList<Cours> filteredList = FXCollections.observableArrayList();
    filteredList.clear();
    for (Cours Skill : list) {
        if (String.valueOf(Skill.getNom()).contains(rec.getText()) ||
            (Skill.getIntensite()).contains(rec.getText())) {
            filteredList.add(Skill);
        }
    }
    idcr.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomcr.setCellValueFactory(new PropertyValueFactory<>("nom"));
    dureecr.setCellValueFactory(new PropertyValueFactory<>("duree"));
    intcr.setCellValueFactory(new PropertyValueFactory<>("intensite"));
    biencr.setCellValueFactory(new PropertyValueFactory<>("bienfaits"));
    imgcr.setCellValueFactory(new PropertyValueFactory<>("image"));

    affiche.setItems(filteredList);
}


    @FXML
    private void rec(KeyEvent event) {
    }

    @FXML
    private void affichrtTri(ActionEvent event) {
        ServiceCr rs = new ServiceCr();
      List<Cours> l = new ArrayList<>();
    l = (ArrayList<Cours>) rs.afficherCr();
    ObservableList<Cours> data = FXCollections.observableArrayList(l);
    data.sort((r1, r2) -> r1.getNom().compareTo(r2.getNom()));
    FilteredList<Cours> fle = new FilteredList(data, e -> true);
    affiche.setItems(fle);
}

    @FXML
    private void GoToPDF(ActionEvent event) throws IOException {
              Parent root = FXMLLoader.load(getClass().getResource("affCours.fxml"));
        Scene scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
}
