/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.ServiceRes;

public class StatisticsController implements Initializable {

    ServiceRes rs = new ServiceRes();
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private BorderPane borderPane;
private static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    @FXML
    private MenuItem bb;





    

    @FXML
    public void handleShowPieChart() throws SQLException {
        Node centerNode = borderPane.getCenter();
PieChart pieChart = null;

if (centerNode instanceof PieChart) {
    pieChart = (PieChart) centerNode;
} else {
    pieChart = new PieChart();
    borderPane.setCenter(pieChart);
}


 List<Integer> countsByMonth = rs.getReservationCountByMonth();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    for (int i = 0; i < countsByMonth.size(); i++) {
        pieChartData.add(new PieChart.Data(MONTH_NAMES[i], countsByMonth.get(i)));
    }

    pieChart.setData(pieChartData);
    }



    @FXML
    public void handleShowBarChart() throws SQLException {
   List<Integer> countsByMonth = rs.getReservationCountByMonth();
    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    for (int i = 0; i < countsByMonth.size(); i++) {
        series.getData().add(new XYChart.Data<>(MONTH_NAMES[i], countsByMonth.get(i)));
    }
    barChart.getData().clear();
    barChart.getData().add(series);    
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialization of the controller
    }
    
 @FXML
public void handleUpdatePieData(ActionEvent event) throws SQLException {
     Node centerNode = borderPane.getCenter();
PieChart pieChart = null;

if (centerNode instanceof PieChart) {
    pieChart = (PieChart) centerNode;
} else {
    pieChart = new PieChart();
    borderPane.setCenter(pieChart);
}
    int year = LocalDate.now().getYear();
    List<Integer> countsByMonth = rs.getReservationCountByMonth();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    for (int i = 0; i < countsByMonth.size(); i++) {
        pieChartData.add(new PieChart.Data(MONTH_NAMES[i], countsByMonth.get(i)));
    }
    
    // get a reference to the pieChart object

    // set the data for the pieChart object
    pieChart.setData(pieChartData);
}

    @FXML
    public void handleClose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close the application?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
        System.exit(0);
        }
    }

@FXML
private void Back(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/reservation.fxml"));
    Parent root = loader.load();
    ReservationController reservationController = loader.getController();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
    stage.setScene(scene);
    stage.show();
}


}
