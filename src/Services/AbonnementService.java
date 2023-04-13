/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;
import Entities.Abonnement;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HanaM
 */
public class AbonnementService implements IService<Abonnement>{
    
    Connection cnx;

    public AbonnementService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Abonnement a) throws SQLException {        
        String req = "INSERT INTO Abonnement (nom_a, type_a, prix_a, description_a, debut_a, fin_a) VALUES ('"+a.getNom_a()+"','"+a.getType_a()+"','"+a.getPrix_a()+"','"+a.getDescription_a()+"','"+a.getDebut_a()+"','"+a.getFin_a()+"') ";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Abonnement a) throws SQLException {
        String req = "UPDATE Abonnement SET nom_a = ?, type_a = ?, prix_a = ?, description_a = ?, debut_a = ?, fin_a = ? WHERE ID = ?";
        PreparedStatement as = cnx.prepareStatement(req);
        as.setString(1, a.getNom_a());
        as.setString(2, a.getType_a());
        as.setInt(3, a.getPrix_a());
        as.setString(4, a.getDescription_a());
        as.setDate(5, a.getDebut_a());
        as.setDate(6, a.getFin_a());
        as.setInt(7, a.getId());
        as.executeUpdate();
    }
    

    @Override
    public void supprimer(Abonnement a) throws SQLException {
        String req = "DELETE FROM Abonnement WHERE id = ?";
        PreparedStatement as = cnx.prepareStatement(req);
        as.setInt(1, a.getId());
        as.executeUpdate();
    }

    @Override
    public List<Abonnement> recuperer() throws SQLException {
        
        List<Abonnement> Abonnements = new ArrayList<>();
        
        String req = "SELECT * FROM Abonnement";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            Abonnement a = new Abonnement();
            a.setId(rs.getInt("id"));
            a.setNom_a(rs.getString("nom_a"));
            a.setType_a(rs.getString("type_a"));
            a.setPrix_a(rs.getInt("prix_a"));
            a.setDescription_a(rs.getString("description_a"));
            a.setDebut_a(rs.getDate("debut_a"));
            a.setFin_a(rs.getDate("fin_a"));
            
            Abonnements.add(a);
        }
        
        return Abonnements;
    }
       
    public List<Abonnement> recupererById(int id) throws SQLException {
        
        List<Abonnement> Abonnements = new ArrayList<>();
        
        String req = "SELECT * FROM Abonnement WHERE id = ?";
        PreparedStatement as = cnx.prepareStatement(req);
        as.setInt(1, id);
        ResultSet rs = as.executeQuery();
        
        while(rs.next()){
            Abonnement a = new Abonnement();
            a.setId(rs.getInt("id"));
            a.setNom_a(rs.getString("nom_a"));
            a.setType_a(rs.getString("type_a"));
            a.setPrix_a(rs.getInt("prix_a"));
            a.setDescription_a(rs.getString("description_a"));
            a.setDebut_a(rs.getDate("debut_a"));
            a.setFin_a(rs.getDate("fin_a"));
            
            Abonnements.add(a);
        }
        
        return Abonnements;
    }
    
}
