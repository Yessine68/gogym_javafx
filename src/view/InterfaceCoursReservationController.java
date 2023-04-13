/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class InterfaceCoursReservationController implements Initializable {

    @FXML
    private Button cour;
    @FXML
    private Button resrr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void crr(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cours.fxml"));
                Parent root = loader.load();
                CoursController af = loader.getController();
                cour.getScene().setRoot(root);
    }

    @FXML
    private void reser(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reservation.fxml"));
                Parent root = loader.load();
                ReservationController a = loader.getController();
                resrr.getScene().setRoot(root);
    }
    
}
