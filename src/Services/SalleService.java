/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;
import Entities.Salle;
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

public class SalleService implements IService<Salle> {
    
    Connection cnx;

    public SalleService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Salle s) throws SQLException {
        String req = "INSERT INTO Salle (nom_s, email_s, tel_s, adresse_s, ville_s, image_s, perimetre_s, like_s) VALUES ('"+s.getNom_s()+"','"+s.getEmail_s()+"',"+s.getTel_s()+",'"+s.getAdresse_s()+"','"+s.getVille_s()+"','"+s.getImage_s()+"',"+s.getPerimetre_s()+","+s.getLike_s()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Salle s) throws SQLException {
        String req = "UPDATE Salle SET nom_s = ?, email_s = ?, tel_s = ?, adresse_s = ?, ville_s = ?, image_s = ?, perimetre_s = ?, like_s = ? WHERE ID = ?";
        PreparedStatement ss = cnx.prepareStatement(req);
        ss.setString(1, s.getNom_s());
        ss.setString(2, s.getEmail_s());
        ss.setInt(3, s.getTel_s());
        ss.setString(4, s.getAdresse_s());
        ss.setString(5, s.getVille_s());
        ss.setString(6, s.getImage_s());
        ss.setFloat(7, s.getPerimetre_s());
        ss.setInt(8, s.getLike_s());
        ss.setInt(9, s.getId());
        ss.executeUpdate();
    }

    @Override
    public void supprimer(Salle s) throws SQLException {
        String req = "DELETE FROM Salle WHERE id = ?";
        PreparedStatement ss = cnx.prepareStatement(req);
        ss.setInt(1, s.getId());
        ss.executeUpdate();
    }

    @Override
    public List<Salle> recuperer() throws SQLException {
        
        List<Salle> Salles = new ArrayList<>();
        
        String req = "SELECT * FROM Salle";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            Salle s = new Salle();
            s.setId(rs.getInt("id"));
            s.setNom_s(rs.getString("nom_s"));
            s.setEmail_s(rs.getString("email_s"));
            s.setTel_s(rs.getInt("tel_s"));
            s.setAdresse_s(rs.getString("adresse_s"));
            s.setVille_s(rs.getString("ville_s"));
            s.setImage_s(rs.getString("image_s"));
            s.setPerimetre_s(rs.getFloat("perimetre_s"));
            s.setLike_s(rs.getInt("like_s"));
            
            Salles.add(s);
        }
        
        return Salles;
    }
       
    public List<Salle> recupererById(int id) throws SQLException {
        
        List<Salle> Salles = new ArrayList<>();
        
        String req = "SELECT * FROM Salle WHERE id = ?";
        PreparedStatement ss = cnx.prepareStatement(req);
        ss.setInt(1, id);
        ResultSet rs = ss.executeQuery();
        
        while(rs.next()){
            Salle s = new Salle();
            s.setId(rs.getInt("id"));
            s.setNom_s(rs.getString("nom_s"));
            s.setEmail_s(rs.getString("email_s"));
            s.setTel_s(rs.getInt("tel_s"));
            s.setAdresse_s(rs.getString("adresse_s"));
            s.setVille_s(rs.getString("ville_s"));
            s.setImage_s(rs.getString("image_s"));
            s.setPerimetre_s(rs.getFloat("perimetre_s"));
            s.setLike_s(rs.getInt("like_s"));
            
            Salles.add(s);
        }
        
        return Salles;
    }

}