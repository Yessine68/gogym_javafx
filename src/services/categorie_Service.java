package services;
import java.sql.*;

import entities.categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Jdbc_connection;


public class categorie_Service {
    
    Connection cnx;
    public categorie_Service() {
            cnx = Jdbc_connection.getInstance();

    }
   
    
    public void insert(categorie e) {
        try {
            PreparedStatement ste = cnx.prepareStatement("INSERT INTO categorie (nom) VALUES (?)");
            ste.setString(1, e.getNom());
            ste.executeUpdate();
            System.out.println("categorie a été inséré avec succès!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void update(categorie categorie) {

        String query = "UPDATE categorie SET nom = ? WHERE id = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setString(1, categorie.getNom());
            stmt.setInt(2, categorie.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public ObservableList<categorie> readAll() {
        ObservableList<categorie> categorie = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM categorie";
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                categorie categorie1 = new categorie(id, nom);
                categorie.add(categorie1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorie;
    }
    public void delete(int e) {
        try {

            String deleteQuery = "DELETE FROM categorie WHERE id = ?";
            PreparedStatement deleteStatement = cnx.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, e);
            deleteStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression  : " + ex.getMessage());
        }
    }

    public categorie readById(int idd) {
        try {
            String query = "SELECT * FROM categorie where id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, idd);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                categorie categorie1 = new categorie(id, nom);
                return categorie1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public categorie readByNom(String nomm) {
        try {
            String query = "SELECT * FROM categorie where nom = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, nomm);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                categorie categorie1 = new categorie(id, nom);
                return categorie1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }





        }

       

