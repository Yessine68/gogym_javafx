/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategorieEvenement;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class CategorieEvenementService {
     Connection cnx;

    public  CategorieEvenementService() {
        cnx = MyConnexion.getInstance().getCnx();

    }
    
    
    public void ajouterCategorieEvenement(String c) {
        try {
            String query = "INSERT INTO categorie_evenement (nom_cat_e) VALUES (?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, c);
            statement.executeUpdate();
            System.out.println("CategorieEvenement created !");
        } catch (SQLException ex) {
            System.out.println("Error while inserting the category: " + ex.getMessage());
        } 
    }
    /*
    public void ajouterCategorieEvenement(String t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `categorie_evenement`( `nom_cat_e`) "
                    + "VALUES ('" + t+ "')";
            st.executeUpdate(query);
            System.out.println("CategEvent ajouté avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    */
    
    
    /*
     public void modifier(long id_amodifier, String t) {
        try {

            PreparedStatement st;
                  st = cnx.prepareStatement("UPDATE `categorie_evenement` SET `nom_cat_e`=? WHERE id=?");

            st.setString(1, t);

            st.setLong(2, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("event modifier avec success");
            } else {
                System.out.println("event n'existe pas");
            }
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());        }

    }
     */
    
    
       
    public void update(CategorieEvenement c) {
        try {
            String query = "UPDATE categorie_evenement SET nom_cat_e = ? WHERE id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, c.getNom_cat_e());
            statement.setInt(2, c.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error while updating the category: " + ex.getMessage());
        }
    }
    
    
   
public List<CategorieEvenement> readAll() {
    List<CategorieEvenement> categories = new ArrayList<>();
    String sql = "SELECT * FROM categorie_evenement";
    try (PreparedStatement stmt = cnx.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom_cat_e = rs.getString("nom_cat_e");
            CategorieEvenement c = new CategorieEvenement(id, nom_cat_e);
            categories.add(c);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return categories;
}

    
     
    public void delete(CategorieEvenement c) {
        try {
            PreparedStatement ps = cnx.prepareStatement("DELETE FROM categorie_evenement WHERE id = ?");
            ps.setInt(1, c.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error while deleting the category: " + ex.getMessage());
        }
    }
     
     
     
     
     
     
     
     
     
     
     
     
     /*
   public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from categorie_evenement where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("evenement n'existe pas");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }   
   */
   
   /*
   public List<CategorieEvenement> afficher() {
        List<CategorieEvenement> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from categorie_evenement";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CategorieEvenement u = new CategorieEvenement();

                u.setNom_cat_e(rs.getString("nom_cat_e"));
               u.setId(rs.getInt("id"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lu;
    }*/
    
   
   
     public int getbynom(String nom) {
int id = 0  ; 
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `categorie_evenement` WHERE `nom_cat_e`='" + nom + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CategorieEvenement u = new CategorieEvenement();

                u.setNom_cat_e(rs.getString("nom_cat_e"));
               u.setId(rs.getInt("id"));
               id=rs.getInt("id") ; 
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        return id;
    }
    
    
    
      public String getbyid(int id) {
String nom =""  ; 
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `categorie_evenement` WHERE `id`='" + id + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CategorieEvenement u = new CategorieEvenement();

                u.setNom_cat_e(rs.getString("nom_cat_e"));
               u.setId(rs.getInt("id"));
               nom=rs.getString("nom_cat_e") ; 
            }
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return nom;
    }

    
    
    
     
}
