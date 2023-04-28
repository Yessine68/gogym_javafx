/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author 21654
 */
public class FXMain extends Application {
    

    
    private Stage stage;
    private Parent parent;
    @Override
    public void start(Stage primaryStage) throws IOException {
       this.stage=primaryStage;
    // parent=FXMLLoader.load(getClass().getResource("Ibrahimhome.fxml"));
      //parent=FXMLLoader.load(getClass().getResource("Eventfront.fxml"));
         parent=FXMLLoader.load(getClass().getResource("Login.fxml"));
       // parent=FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        primaryStage.setTitle("GOGYM app!");
         Image icon = new Image(getClass().getResourceAsStream("gogymlogo-removebg-preview.png"));
          primaryStage.getIcons().add(icon);
        Scene scene=new Scene(parent);
        stage.setScene(scene);
        stage.show();         
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


