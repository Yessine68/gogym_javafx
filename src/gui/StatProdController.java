/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.activation.DataSource;
import services.*;
/**
 * FXML Controller class
 *
 * @author ademm
 */
public class StatProdController implements Initializable {

     @FXML
    private Label labelstat;
    @FXML
    private PieChart piechart;
    
   
    ObservableList<PieChart.Data> data=FXCollections.observableArrayList();
    int n;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          stat();
    }

    
     private void stat() {
        produit_service service  = new produit_service();
        
        data = service.stat(); 
        piechart.setLegendSide(Side.TOP);
        piechart.setData(data);
    }

   @FXML
   public void retour(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("GestionProduit.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }

    
    
}
