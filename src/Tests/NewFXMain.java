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
        Parent root;
        Parent root2;
        try {
            root = FXMLLoader.load(getClass().getResource("/GUI/Admin/GoGym.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Admin interface");
            primaryStage.setScene(scene);
            primaryStage.show();
            root2 = FXMLLoader.load(getClass().getResource("/GUI/Client/GoGymClient.fxml"));
            Scene scene2 = new Scene(root2);
            Stage primaryStage2 = new Stage();
            primaryStage2.setTitle("Client interface");
            primaryStage2.setScene(scene2);
            primaryStage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void openNewWindowAbonnement(Stage primaryStage, Parent root) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Abonnement.fxml"));
            Parent newRoot = loader.load();
            Stage newStage = new Stage();
            newStage.setTitle("Abonnement");
            newStage.setScene(new Scene(newRoot));
            newStage.initOwner(primaryStage);
            newStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to load FXML file");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }    
    }
        
    private void openNewWindowSalle(Stage primaryStage, Parent root) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Salle.fxml"));
            Parent newRoot = loader.load();
            Stage newStage = new Stage();
            newStage.setTitle("Salle");
            newStage.setScene(new Scene(newRoot));
            newStage.initOwner(primaryStage);
            newStage.show();
        } 
        catch (IOException ex) {
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
