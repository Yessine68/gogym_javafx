/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author don7a
 */
public class NewFXMain extends Application {
   
    public static User connectedUser;
    
    @Override
    public void start(Stage primaryStage) {
        connectedUser = new User();
    // rest of the code
        try {
                    //    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Ibrahimhome.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ForgotPassword.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("GO GYM");
       
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());

        }
        
  
   
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
