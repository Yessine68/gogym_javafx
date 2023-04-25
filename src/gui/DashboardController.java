/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entities.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.UserService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 * FXML Controller class
 *
 * @author don7a
 */
public class DashboardController implements Initializable {

    private User user;
    @FXML
    private TableColumn<?, ?> Cid;
    @FXML
    private TableColumn<?, ?> cUsername;
    @FXML
    private TableColumn<?, ?> cName;
    @FXML
    private TableColumn<?, ?> cLastName;
    @FXML
    private TableColumn<?, ?> cEmail;
    @FXML
    private TableView<User> tbUsers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ListeUsers();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
    private List<User> usersList;

    public void ListeUsers() throws SQLException {
        UserService hrc = new UserService();

        Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        cName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cLastName.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        boolean deleteColumnExists = false;
        boolean blockColumnExists = false;

        for (TableColumn column : tbUsers.getColumns()) {
            if (column.getText().equals("ACTION")) {
                deleteColumnExists = true;
                break;
            }
        }

        if (!deleteColumnExists) {
            TableColumn<User, Void> deleteColumn = new TableColumn<>("ACTION");
            deleteColumn.setCellFactory(column -> {
                return new TableCell<User, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction(event -> {
                            User u = getTableView().getItems().get(getIndex());
                            UserService us = new UserService();
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Delete Account");
                            alert.setHeaderText("Are you sure you want to delete this account?");
                            alert.setContentText("This action cannot be undone.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                try {
                                    us.aaaa(u.getId());

                                    refreshTable();
                                } catch (SQLException ex) {
                                    Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                // deletion failed, display an error message
                                /*Alert errorAlert = new Alert(AlertType.ERROR);
                                errorAlert.setTitle("Deletion Error");
                                errorAlert.setHeaderText(null);
                                errorAlert.setContentText("Unable to delete User".");
                                errorAlert.showAndWait();*/
                                alert.close();
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
            });

            tbUsers.getColumns().add(deleteColumn);
        }
        if (!blockColumnExists) {
            TableColumn<User, Void> blockColumn = new TableColumn<>("STATUS");
            blockColumn.setCellFactory(column -> {
                return new TableCell<User, Void>() {
                    private final Button blockButton = new Button("Enabled");

                    {
                        blockButton.setOnAction(event -> {
                            // Get the User associated with this row
                            User user = getTableView().getItems().get(getIndex());
                            System.out.println(user);
                            System.out.println(user.getStatus());
                                //System.out.println(user);
                            // Set the blocked flag on the user object
                               if (user.getStatus().equals("enabled"))
                                       user.setStatus("disabled");
                               else 
                                     user.setStatus("enabled");

                            // Update the database or perform any other action to prevent the user from logging in
                            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "")) {
                                String sql = "UPDATE user SET status = ? WHERE id = ?";
                                PreparedStatement statement = connection.prepareStatement(sql);
                                statement.setString(1, user.getStatus());
                                statement.setInt(2, user.getId());
                                int rowsUpdated = statement.executeUpdate();
                                System.out.println("Rows updated: " + rowsUpdated);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            blockButton.setText(user.getStatus());
//                            if (user.getStatus().equals("disabled")) {
//                                blockButton.setText("Enable");
//                            } else {
//                                blockButton.setText("Disable");
//                            }
                            // Clear the cell content to indicate the user is blocked
//                            setGraphic(null);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {                            blockButton.setText(user.getStatus());

//                            if (user.getStatus().equals("disabled")) {
//                                
//                                blockButton.setText("Disable");
//                            } else {
////                                System.out.println(user);
//                                blockButton.setText("Enable");
//                            }
                            setGraphic(blockButton);
                        }
                    }
                };

            });

            tbUsers.getColumns().add(blockColumn);
        }
        List<User> list = hrc.recuperer();
        ObservableList<User> observableList = FXCollections.observableArrayList(list);
        tbUsers.setItems(observableList);
    }

    private void refreshTable() {
        try {
            usersList = new UserService().recuperer();
            tbUsers.setItems(FXCollections.observableArrayList(usersList));
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
   @FXML
    public void Logout(ActionEvent event) throws IOException {
        // Load the login screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController LoginController = loader.getController();

        Scene scene = new Scene(root);

        // Switch to the login screen
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
