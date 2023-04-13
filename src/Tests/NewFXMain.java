/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 *
 * @author HanaM
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GoGym.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root); 
            primaryStage.setTitle("Go Gym");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to load FXML file");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
