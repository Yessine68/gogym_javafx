package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Jdbc_connection;
import entities.Produit;
import utils.Log;
;

public class produit_service {
   
    Connection cnx;
    public produit_service() {
        cnx = Jdbc_connection.getInstance();
    }
   categorie_Service service = new categorie_Service();

    public void add_produit (Produit produit ) {
        try {
            String sql = "insert into produit(categorie_id,nom_prod,description,prix,nbr_prods,image)"
                    + "values (?,?,?,?,?,?)";
             PreparedStatement ste = cnx.prepareStatement(sql);


            categorie cat = service.readByNom(produit.getCategorie());
            ste.setInt(1, cat.getId());
            ste.setString(2, produit.getNom_prod());
            ste.setString(3, produit.getDescription());
            ste.setInt(4, produit.getPrix());
            ste.setInt(5, produit.getNbr_prods());
            ste.setString(6, produit.getImage());
            ste.executeUpdate();
        Log.console("Produit ajoutée");
        } catch (SQLException ex) {
            Log.file(ex.getMessage());ex.printStackTrace();
            System.err.println("Erreur lors de l'ajout  : " + ex.getMessage());
        }
    }
    public ObservableList<Produit> readAll() {
        ObservableList<Produit> produits = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM produit";
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom_prod");
                String desc = resultSet.getString("description");
                int prix = resultSet.getInt("prix");
                int nbr_prods = resultSet.getInt("nbr_prods");
                int catid = resultSet.getInt("categorie_id");
                categorie categorie = service.readById(catid);
                String cat = categorie.getNom();
                String img = resultSet.getString("image");
                Produit produit = new Produit(id, nom, desc,prix,nbr_prods,cat,img);
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

   public void delete(int e) {
        try {

            String deleteQuery = "DELETE FROM produit WHERE id = ?";
            PreparedStatement deleteStatement = cnx.prepareStatement(deleteQuery);
            deleteStatement.setInt(1,e);
            deleteStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression  : " + ex.getMessage());
        }
    }

    public void update( Produit produit) {

        String query = "UPDATE produit SET nom_prod = ?, description = ?,prix = ?, nbr_prods=? ,categorie_id=? WHERE id = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setString(1, produit.getNom_prod());
            stmt.setString(2, produit.getDescription());
            stmt.setInt(3, produit.getPrix());
            stmt.setInt(4, produit.getNbr_prods());
            //stmt.setString(5, produit.getImage());
            String cat = produit.getCategorie();
            categorie categorie = service.readByNom(cat);
            stmt.setInt(5, categorie.getId());
            stmt.setInt(6, produit.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }




    }