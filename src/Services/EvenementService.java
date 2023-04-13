/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Services.*;
/**
 *
 * @author MSI
 */
public class EvenementService {
    
    Connection cnx;

    public EvenementService() {
        cnx = MyConnexion.getInstance().getCnx();

    }
    
    public void ajouterEvenement(Evenement e) {
        try {
            String query = "INSERT INTO evenement (categorie_evenement_id, nom_e, date_e, description_e, lieu_e, nbr_participants, Etat, image) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            
            String cat = e.getCategorieEvenement();
            CategorieEvenementService service = new CategorieEvenementService();
            statement.setInt(1, service.getbynom(cat));
            statement.setString(2, e.getNom_e());
            statement.setString(3, e.getDate_e());
            statement.setString(4, e.getDescription_e());
            statement.setString(5, e.getLieu_e());
            statement.setInt(6, e.getNbr_participants());
            statement.setString(7, "");
            statement.setString(8, e.getImage());
            statement.executeUpdate();
            System.out.println("Evenement créé !");
        } catch (SQLException ex) {
            System.out.println("Error while creating the event: " + ex.getMessage());
        }
    }
    
    public void update(Evenement e) {
try {
String query = "UPDATE evenement SET categorie_evenement_id = ?, nom_e = ?, date_e = ?, description_e = ?, lieu_e = ?, nbr_participants = ?, Etat = ?, image = ? WHERE id = ?";
PreparedStatement statement = cnx.prepareStatement(query);

String cat = e.getCategorieEvenement();
CategorieEvenementService service = new CategorieEvenementService();
statement.setInt(1, service.getbynom(cat));
statement.setString(2, e.getNom_e());
statement.setString(3, e.getDate_e());
statement.setString(4, e.getDescription_e());
statement.setString(5, e.getLieu_e());
statement.setInt(6, e.getNbr_participants());
statement.setString(7, "");
statement.setString(8, e.getImage());
statement.setInt(9, e.getId());
statement.executeUpdate();
} catch (SQLException ex) {
System.out.println("Error while updating the event: " + ex.getMessage());
}
}
    
    
    public List<Evenement> readAll() {
    List<Evenement> evenements = new ArrayList<>();
    String sql = "SELECT * FROM evenement";
    try (PreparedStatement stmt = cnx.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            int id = rs.getInt("id");
            int catid = rs.getInt("categorie_evenement_id");
            CategorieEvenementService service = new CategorieEvenementService();
            String categorieEvenement = service.getbyid(catid);
            String nom_e = rs.getString("nom_e");
            String date_e = rs.getString("date_e");
            String description_e = rs.getString("description_e");
            String lieu_e = rs.getString("lieu_e");
            int nbr_participants = rs.getInt("nbr_participants");
            String Etat = rs.getString("Etat");
            String image = rs.getString("image");
            Evenement e = new Evenement(id, categorieEvenement, nom_e, date_e, description_e, lieu_e, nbr_participants, Etat, image);
            evenements.add(e);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return evenements;
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    public void ajouterEvent(Evenement t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `evenement`(`categorie_evenement_id`,`nom_e`, `date_e`, `description_e`, `lieu_e`, `nbr_participants`, `image`, `etat`) "
                    + "VALUES ('" + t.getCategorieEvenement() + "','" + t.getNom_e() + "','" + t.getDate_e() + "','" + t.getDescription_e() + "','" + t.getLieu_e() + "','" + t.getNbr_participants() + "','" + (t.getImage()) + "','" + t.getEtat() + "')";
            st.executeUpdate(query);
            System.out.println("Event ajouté avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    */
    
    /*
 public void modifier(long id_amodifier, Evenement t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `evenement` SET `categorie_evenement_id`=?,`nom_e`=?, `date_e`=?,`description_e`=?,`lieu_e`=?,`nbr_participants`=?,`image`=? WHERE id=?");
            System.out.println("2");

            st.setInt(1, t.getCategorieEvenement());
            st.setString(2, t.getNom_e());
            st.setString(3, t.getDate_e());
            st.setString(4, t.getDescription_e());
            st.setString(5, t.getLieu_e());
            st.setString(6, t.getNbr_participants());
            st.setString(7, t.getImage());
            st.setLong(8, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("event modifier avec success");
            } else {
                System.out.println("event n'existe pas");
            }
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }

    }   
 */
 public void delete(Evenement e) {
    try {
        PreparedStatement ps = cnx.prepareStatement("DELETE FROM evenement WHERE id = ?");
        ps.setInt(1, e.getId());
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.err.println("Error while deleting the event: " + ex.getMessage());
    }
}

  /* public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from evenement where id=" + id;
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
    public List<Evenement> afficher() {
        List<Evenement> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from evenement";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Evenement u = new Evenement();

                u.setDate_e(rs.getString("date_e"));
                u.setCategorieEvenement(rs.getInt("categorie_evenement_id"));
                u.setId(rs.getInt("id"));
                u.setNom_e(rs.getString("nom_e"));
                u.setDescription_e(rs.getString("description_e"));
                u.setLieu_e(rs.getString("lieu_e"));
                u.setImage(rs.getString("image"));
                u.setEtat(rs.getString("etat"));
                u.setNbr_participants(rs.getString("nbr_participants"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lu;
    }
  */ 
 /*
 public List<Evenement> afficher() {
    List<Evenement> le = new ArrayList<>();
    try {
        Statement st = cnx.createStatement();
        String query = "SELECT * FROM evenement";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Evenement e = new Evenement();
            e.setId(rs.getInt("id"));
            e.setCategorieEvenement(rs.getString("categorie_evenement"));
            e.setNom_e(rs.getString("nom_e"));
            e.setDate_e(rs.getString("date_e"));
            e.setDescription_e(rs.getString("description_e"));
            e.setLieu_e(rs.getString("lieu_e"));
            e.setNbr_participants(rs.getInt("nbr_participants"));
            e.setEtat(rs.getString("Etat"));
            e.setImage(rs.getString("image"));
            le.add(e);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return le;
}
*/
 
 
 /*
 public String findByIdimage(int username) {
        Evenement u = new Evenement();
        String email = " ";
        try {
            Statement st = cnx.createStatement();
            String query = "select * from evenement where id='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                email = rs.getString("image");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return email;
    }   
    
    
 
 public Evenement EventDetailFront(int username) {
        Evenement u = new Evenement();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from evenement where id='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                u.setDate_e(rs.getString("date_e"));
                u.setCategorieEvenement(rs.getInt("categorie_evenement_id"));
                u.setId(rs.getInt("id"));
                u.setNom_e(rs.getString("nom_e"));
                u.setDescription_e(rs.getString("description_e"));
                u.setLieu_e(rs.getString("lieu_e"));
                u.setImage(rs.getString("image"));
                u.setEtat(rs.getString("etat"));
                u.setNbr_participants(rs.getString("nbr_participants"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }
 
 */
    
}
