package pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainInterfaceController  implements Initializable {

    @FXML
    private Button btnajout;

    @FXML
    private Button btnajout1;

    @FXML
    private Button btnajout11;

    @FXML
    private Button btnajout111;

    @FXML
    private GridPane grid1;

    @FXML
    private Pane pnlCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gererProduit(null);
    }
    @FXML
    void ajouterCategorie(ActionEvent event) {
        grid1.getChildren().clear();
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("ajouterCategorie.fxml"));

            AnchorPane anchorPane = cards.load();

            grid1.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void ajouterProduit(ActionEvent event) {
        grid1.getChildren().clear();
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("ajouterProduit.fxml"));

            AnchorPane anchorPane = cards.load();

            grid1.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void gererCategorie(ActionEvent event) {
        grid1.getChildren().clear();
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("gererCategorie.fxml"));

            AnchorPane anchorPane = cards.load();

            grid1.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void gererProduit(ActionEvent event) {
        grid1.getChildren().clear();
        try {
            // TODO
            FXMLLoader cards = new FXMLLoader();
            cards.setLocation(getClass().getResource("gererProduit.fxml"));

            AnchorPane anchorPane = cards.load();

            grid1.add(anchorPane, 1, 1);

            GridPane.setMargin(anchorPane, new javafx.geometry.Insets(10));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
