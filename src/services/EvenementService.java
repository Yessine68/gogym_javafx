/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.User;
import entities.Evenement;
import utils.MyDB;
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
import services.*;
import java.util.Comparator;
import java.util.stream.Collectors;
/**
 *
 * @author MSI
 */
public class EvenementService {
        //int userid = 1 ;
 

    Connection cnx;

    public EvenementService() {
        cnx = MyDB.getInstance().getCnx();

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
    
        
    public List<Evenement> recherche(String nomevent ) {
        List<Evenement> list = new ArrayList<>();
          try {
            String req = "Select * from evenement";
            PreparedStatement statement = cnx.prepareStatement(req);
            ResultSet rs = statement.executeQuery();
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
            list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        list=list.stream().filter(e -> e.getNom_e().contains(nomevent) || e.getLieu_e().contains(nomevent)).collect(Collectors.toList());
        
        return list;
    }
     
     public List<Evenement> Trie(String sortOrder , List<Evenement> list) {
       if(sortOrder=="ASC"){         
       list = list.stream().sorted((o1, o2)->o1.getNom_e().
                                   compareTo(o2.getNom_e())).
                                   collect(Collectors.toList());
       

       }
       else{     list = list.stream()
                                        .sorted(Comparator.comparing(Evenement::getNom_e).reversed())
                                         .collect(Collectors.toList());
       }
  
           
       
        return list;
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


    
  
 public void delete(Evenement e) {
    try {
        PreparedStatement ps = cnx.prepareStatement("DELETE FROM evenement WHERE id = ?");
        ps.setInt(1, e.getId());
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.err.println("Error while deleting the event: " + ex.getMessage());
    }
}

 
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
    
    */
 
 public Evenement EventDetailFront(int username) {
        Evenement u = new Evenement();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from evenement where id='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                u.setDate_e(rs.getString("date_e"));
                u.setCategorieEvenement(rs.getString("categorie_evenement_id"));
                u.setId(rs.getInt("id"));
                u.setNom_e(rs.getString("nom_e"));
                u.setDescription_e(rs.getString("description_e"));
                u.setLieu_e(rs.getString("lieu_e"));
                u.setImage(rs.getString("image"));
                u.setEtat(rs.getString("etat"));
                u.setNbr_participants(rs.getInt("nbr_participants"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }
 
public void decrement(Evenement e) {
    try {
        String query = "UPDATE evenement SET nbr_participants = nbr_participants - 1 WHERE id = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setInt(1, e.getId());
        statement.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("Error while updating the event: " + ex.getMessage());
    }
}
 
 public void Participer(Evenement e, String verification,User u) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `participate`(`id_user_id`,`id_event_id`, `verification_code`) "
                    + "VALUES ('" + u.getId() + "','" + e.getId() + "','" + verification + "')";
            st.executeUpdate(query);
            System.out.println("participation ajouté avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 

 
 
   public Boolean check(int Eventid , int userid){
          try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `participate` WHERE `id_user_id`='" + userid + "' AND `id_event_id`='" + Eventid + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

   
   

   
 
   

}
   
   
 
             