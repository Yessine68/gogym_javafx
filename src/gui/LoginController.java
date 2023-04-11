/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import services.UserService;
import utils.MyDB;


/**
 * FXML Controller class
 *
 * @author don7a
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button LoginButton;
    Connection con;
    PreparedStatement pst;
    private User user;
    
    
    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    
     public void setUser(User user) {
        this.user = user;
        usernameTF.setText(user.getUsername());
    }

//@FXML
//public void Login1(ActionEvent event) throws ClassNotFoundException, SQLException {
//    String username = usernameTF.getText();
//    String password = passwordTF.getText();
//
//    String req = "SELECT * FROM users WHERE username=? AND password=?";
//
////    PreparedStatement ps = con.prepareStatement(req);
//    pst = con.prepareStatement(req);
//    pst.setString(1, username);
//    pst.setString(2, password);
//    ResultSet rs = pst.executeQuery();
//    
//    if (rs.next()) {
//       
//        User user = new User();
//        user.setId(rs.getInt("id"));
//        user.setUsername(rs.getString("username"));
//        user.setPassword(rs.getString("password"));
//        user.setEmail(rs.getString("email"));
//        user.setNom(rs.getString("nom"));
//        user.setPrenom(rs.getString("prenom"));
//        user.setStatus(rs.getString("status"));
//        user.setResetToken(rs.getString("reset_token"));
//        user.setRoles(rs.getString("roles").split(","));
//         try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
//            Parent root = loader.load();
//            usernameTF.getScene().setRoot(root);
//           
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }
//        // do something with the logged in user, e.g. show a new window or update UI
//        // ...
//    } else {
//        // handle invalid login credentials, e.g. show an error message
//        // ...
//    }
//}
     
     
     
     
     
     
     
     
     
     
     
     
@FXML  
private void Login(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    String nom = usernameTF.getText();
    String password = passwordTF.getText();

    if (nom.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter both username and password");
    } else {
        try {
            UserService userService = new UserService();
            User loggedInUser = userService.login(nom, password);
            if (loggedInUser != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                Parent root = loader.load();
                ProfileController controller = loader.getController();
                controller.setUser(loggedInUser);
                usernameTF.getScene().setRoot(root);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while logging in. Please try again later.");
            ex.printStackTrace();
        }
    }
}     
     
     
     
//@FXML
//private void Login (ActionEvent event) throws ClassNotFoundException, SQLException {
//    String nom = usernameTF.getText();
//    String password = passwordTF.getText();
//
//    if (nom.isEmpty() || password.isEmpty()) {
//        JOptionPane.showMessageDialog(null, "Please enter both username and password");
//    } else {
//        try {
//            UserService UserService = new UserService();
//            user loggedIn = UserService.login(nom, password);
//            if (loggedIn) {
//                // Login successful, do something here (e.g. navigate to a new page)
//            } else {
//                // Login failed, show error message
//                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
//            }
//        } catch (SQLException ex) {
//            // Handle SQL exception here (e.g. show error message)
//            JOptionPane.showMessageDialog(null, "An error occurred while logging in. Please try again later.");
//            ex.printStackTrace();
//        }
//    }
//}


    
    
    
    
    



    private void signup(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPersonne.fxml"));
            Parent root = loader.load();
            AjouterPersonneController controller =loader.getController();
            controller.setUsername(usernameTF.getText());
            usernameTF.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void gotoSignup(ActionEvent event) {
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            Parent root = loader.load();
            usernameTF.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}















//    @FXML
//    private void Login(ActionEvent event) throws ClassNotFoundException {
//        String username =usernameTF.getText();
//        String password =passwordTF.getText();
//        
//        if (username.equals("") && password.equals("")){
//        JOptionPane.showMessageDialog(null, "Username or password blank");
//        
//        }else { 
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManaget.getConnection("jdbc:mysql://localhost/superpos", info);
//        
//        }
//            
//        
//
//    }
