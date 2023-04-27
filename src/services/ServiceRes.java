/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cours;
import entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.MyDB;

/**
 *
 * @author Amirov
 */
public class ServiceRes implements ISR<Reservation> {
private Connection cnx ;
 
public ServiceRes() {
    cnx = MyDB.getInstance().getcnx();
}

private Map<LocalDate, Integer> reservations;

    /**
     *
     */
    

    public void addReservation(LocalDate date) {
        int count = reservations.getOrDefault(date, 0);
        reservations.put(date, count + 1);
    }

    public int getReservationCount(LocalDate date) {
        return reservations.getOrDefault(date, 0);
    }

   
    @Override
    public void ajouterRv(Reservation r) {
           try {
             String querry="INSERT INTO `reservation`(`cours_id`, `date`, `type`) VALUES ('"+r.getCours_id()+"','"+r.getDate()+"','"+r.getType()+"')";
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        System.out.println("Reservation ajouter avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
        
    }

    @Override
    public List<Reservation> afficherRv() {
       List<Reservation> reservation = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `reservation`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Reservation a = new Reservation();
            a.setId(rs.getInt(1));
            a.setCours_id(rs.getInt(2));
            a.setDate(rs.getDate(3));
            a.setType(rs.getString(4));
            

            
            
            reservation.add(a);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return reservation;

    }


    @Override
    public void modiferRv(Reservation r) {
        try {
            String querry = "UPDATE `reservation` SET `id`='" + r.getId()+"', `cours_id`='" + r.getCours_id()+ "' , `date`='" + r.getDate()+ "', `type`='" + r.getType()+ "' WHERE id=" + r.getId();
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("La reservation est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
 }

    @Override
    public void supprimerRv(int id) {
  try {
            String querry = "DELETE FROM `reservation` WHERE id="+id;
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("Reservation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Integer> getReservationCountByMonth() throws SQLException {
    List<Integer> counts = new ArrayList<>();
    for (int i = 1; i <= 12; i++) {
        String query = "SELECT COUNT(*) FROM Reservation WHERE MONTH(date) = ?";
        PreparedStatement st = MyDB.getInstance().getcnx().prepareStatement(query);
        st.setInt(1, i);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            counts.add(rs.getInt(1));
        } else {
            counts.add(0);
        }
    }
    return counts;
    }

    public Map<String, Integer> getReservationCountByMonth(int year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
