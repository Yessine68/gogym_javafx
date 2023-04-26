/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Client;

import GUI.Admin.*;
import GUI.Admin.GoGymController;
import Entities.Abonnement;
import Entities.Salle;
import Services.AbonnementService;
import Services.SalleService;
import Utils.MyDB;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HanaM
 */
public class AbonnementClientController implements Initializable {


    AbonnementService as = new AbonnementService();

    ObservableList<Abonnement> obs;
    
    private AnchorPane scrollPane;
    private List<CheckBox> checks = new ArrayList<CheckBox>();
        private List<String> sitesNoms=new ArrayList<String>();
    @FXML
    private Pagination pagination;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane6;
    @FXML
    private Pane pane7;
    @FXML
    private Pane pane8;
    @FXML
    private Pane pane9;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane10;
    @FXML
    private TextField entrySearch;
    @FXML
    private ComboBox<String> searchCombo;
    @FXML
    private ComboBox<String> trieCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        trieCombo.setValue("Id");
        trieCombo.getItems().add("Id");
        trieCombo.getItems().add("Nom");
        trieCombo.getItems().add("Type");
        searchCombo.setValue("Nom");
        searchCombo.getItems().add("Nom");
        
        
        AbonnementService as = new AbonnementService();
        pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> {
            int pageIndex = newValue.intValue();
             try {
                 affichageSalle(pageIndex+1,"","","id");
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(SalleMenuController.class.getName()).log(Level.SEVERE, null, ex);
             }
            System.out.println("Clicked page " + (pageIndex + 1));
        });
        List<Abonnement> abonnements;
        try {
            abonnements = as.recuperer();
            pagination.setPageCount(abonnements.size()/10+1);
        pagination.setCurrentPageIndex(0);
        } catch (SQLException ex) {
            Logger.getLogger(SalleMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            affichageSalle(1,"","","nom_a");
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
                sql = "select nom_a,type_a,prix_a,description_a,debut_a,fin_a from abonnement order by "+ch2+" limit "+a+","+b;
            }
            else {
                sql = "select nom_a,type_a,prix_a,description_a,debut_a,fin_a from abonnement where "+ch+" like '%"+entry+"%' order by "+ch2+"  limit "+a+","+b;
            }
            ste = conn.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);
            int i=0;
            while(rs.next()){
                panes.get(i).setVisible(true);
               ((Text)panes.get(i).getChildren().get(7)).setText(rs.getString(1));
               ((Text)panes.get(i).getChildren().get(8)).setText(rs.getString(2));
                ((Text)panes.get(i).getChildren().get(9)).setText(rs.getString(3));
                ((Text)panes.get(i).getChildren().get(10)).setText(rs.getString(4));
                ((Text)panes.get(i).getChildren().get(11)).setText(String.valueOf(rs.getDate(5)));
                ((Text)panes.get(i).getChildren().get(12)).setText(String.valueOf(rs.getDate(6)));
                i++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SalleMenuController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void paneClicked(MouseEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
        String searchPar = searchCombo.getValue();
        String ch = "";
        if (searchPar.equals("Nom")){
            ch="nom_a";
        }
        String entry = entrySearch.getText();
        pagination.setCurrentPageIndex(0);
        try {
            String ch2 = "";
        if (trieCombo.getValue().equals("Id")){
            ch2="id";
        }
        if (trieCombo.getValue().equals("Nom")){
            ch2="nom_a";
        }
        if (trieCombo.getValue().equals("Type")){
            ch2="type_a";
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
            ch2="nom_a";
        }
        if (trieCombo.getValue().equals("Type")){
            ch2="type_a";
        }
        pagination.setCurrentPageIndex(0);
        try {
            if (entrySearch.getText().equals("")){
                affichageSalle(1,"","",ch2);
            }else {
                String ch = "";
                if (searchCombo.getValue().equals("Nom")){
                    ch="nom_a";
                }
                affichageSalle(1,ch,entrySearch.getText(),ch2);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SalleMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}