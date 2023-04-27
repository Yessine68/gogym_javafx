
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class FXMain extends Application {
    
    private Stage stage;
    private Parent parent;
    @Override
    public void start(Stage primaryStage) throws IOException {
       this.stage=primaryStage;
      parent=FXMLLoader.load(getClass().getResource("Ademhome.fxml"));
   
        primaryStage.setTitle("GOGYM app!");
        Scene scene=new Scene(parent);
        stage.setScene(scene);
        stage.show();         
    }
    

   
    public static void main(String[] args) {
        launch(args);
    }
    
}


