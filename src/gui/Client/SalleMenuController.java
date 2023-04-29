/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Client;

import entities.Salle;
import services.SalleService;
import utils.MyDB;
import utils.Variables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
//import com.sothawo.mapjfx.Projection;
/**
 * FXML Controller class
 *
 * @author HanaM
 */
public class SalleMenuController implements Initializable {

    @FXML
    private Pagination pagination;
SalleService ss = new SalleService();
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane6;
    @FXML
    private Pane pane7;
    @FXML
    private Pane pane8;
    @FXML
    private Pane pane9;
    @FXML
    private Pane pane10;
    @FXML
    private ComboBox<String> searchCombo;
    @FXML
    private ComboBox<String> trieCombo;
    @FXML
    private TextField entrySearch;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        trieCombo.setValue("Id");
        trieCombo.getItems().add("Id");
        trieCombo.getItems().add("Nom");
        trieCombo.getItems().add("Ville");
        searchCombo.setValue("Nom");
        searchCombo.getItems().add("Nom");
        searchCombo.getItems().add("Ville");
        searchCombo.getItems().add("Email");

        pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> {
            int pageIndex = newValue.intValue();
             try {
                 affichageSalle(pageIndex+1,"","","id");
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(SalleMenuController.class.getName()).log(Level.SEVERE, null, ex);
             }
            System.out.println("Clicked page " + (pageIndex + 1));
        });
        List<Salle> salles;
        try {
            salles = ss.recuperer();
            pagination.setPageCount(salles.size()/10+1);
        pagination.setCurrentPageIndex(0);
        } catch (SQLException ex) {
            Logger.getLogger(SalleMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            affichageSalle(1,"","","id");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SalleMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void affichageSalle(int pclicked,String ch,String entry,String ch2) throws FileNotFoundException{
        try {
            List<Pane> panes=new ArrayList<>();
            panes.add(pane1);panes.add(pane2);panes.add(pane3);panes.add(pane4);panes.add(pane5);panes.add(pane6);panes.add(pane7);panes.add(pane8);panes.add(pane9);panes.add(pane10);
            List<ImageView> imageEvent=new ArrayList<>();
            
            for (Pane p : panes){
                p.setVisible(false);
            }
            Connection conn=MyDB.getInstance().getCnx();
            PreparedStatement ste;
            int a=(pclicked-1)*10; 
            int b=pclicked*10;  
            System.out.println("a="+a+"     b="+b);
            String sql=null;
            if (ch.equals("")){
                sql = "select nom_s,email_s,tel_s,ville_s,image_s from salle order by "+ch2+" limit "+a+","+b;
            }
            else {
                sql = "select nom_s,email_s,tel_s,ville_s,image_s from salle where "+ch+" like '%"+entry+"%' order by "+ch2+"  limit "+a+","+b;
            }
            ste = conn.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);
            int i=0;
            while(rs.next()){
                panes.get(i).setVisible(true);
                if (rs.getString(5)!=null){
                    InputStream stream2 = new FileInputStream("./src/Images/Salles/"+rs.getString(5));
                    Image image2 = new Image(stream2);
                    ((ImageView)panes.get(i).getChildren().get(0)).setImage(image2);
                }
                else {
                    imageEvent.get(i).setImage(null);
                }  
               ((Text)panes.get(i).getChildren().get(5)).setText(rs.getString(1));
               ((Text)panes.get(i).getChildren().get(7)).setText(rs.getString(2));
                ((Text)panes.get(i).getChildren().get(8)).setText(rs.getString(3));
                ((Text)panes.get(i).getChildren().get(9)).setText(rs.getString(4));
                //((Text)panes.get(i).getChildren().get(10)).setText(rs.getString(4));
                i++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SalleMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void paneClicked(MouseEvent event) throws SQLException {
        Pane pane = (Pane)event.getSource();
        String paneId = pane.getId();
        int paneOrder = Integer.parseInt(paneId.substring(paneId.indexOf("e")+1));
        List<Salle> salles = ss.recuperer();
        Variables.salleDetail=salles.get((pagination.getCurrentPageIndex())*10+paneOrder-1);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DetailSalle.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage2 = new Stage();
            primaryStage2.setTitle("Detail");
            primaryStage2.setScene(scene);
            primaryStage2.setScene(scene);
            primaryStage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GoGymClient.fxml"));
            Parent root = loader.load();
            GoGymClientController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage primaryStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            primaryStage.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void search(ActionEvent event) throws SQLException {
        String searchPar = searchCombo.getValue();
        String ch = "";
        if (searchPar.equals("Nom")){
            ch="nom_s";
        }
        if (searchPar.equals("Ville")){
            ch="ville_s";
        }
        if (searchPar.equals("Email")){
            ch="email_s";
        }
        String entry = entrySearch.getText();
        pagination.setCurrentPageIndex(0);
        try {
            String ch2 = "";
        if (trieCombo.getValue().equals("Id")){
            ch2="id";
        }
        if (trieCombo.getValue().equals("Nom")){
            ch2="nom_s";
        }
        if (trieCombo.getValue().equals("Ville")){
            ch2="ville_s";
        }
            affichageSalle(1,ch,entry,ch2);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SalleMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void trierPar(ActionEvent event) {
        String ch2 = "";
        if (trieCombo.getValue().equals("Id")){
            ch2="id";
        }
        if (trieCombo.getValue().equals("Nom")){
            ch2="nom_s";
        }
        if (trieCombo.getValue().equals("Ville")){
            ch2="ville_s";
        }
        pagination.setCurrentPageIndex(0);
        try {
            if (entrySearch.getText().equals("")){
                affichageSalle(1,"","",ch2);
            }else {
                String ch = "";
                if (searchCombo.getValue().equals("Nom")){
                    ch="nom_s";
                }
                if (searchCombo.getValue().equals("Ville")){
                    ch="ville_s";
                }
                if (searchCombo.getValue().equals("Email")){
                    ch="email_s";
                }
                affichageSalle(1,ch,entrySearch.getText(),ch2);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SalleMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
