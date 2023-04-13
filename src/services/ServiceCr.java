/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cours;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Amirov
 */
public class ServiceCr  implements ISC<Cours> {
private Connection cnx ;
 
public ServiceCr() {
    cnx = MyDB.getInstance().getcnx();
}

    @Override
    public void ajouterCr(Cours c) {
         try {
             String querry="INSERT INTO `cours`(`duree`, `nom`, `intensite`, `bienfaits`, `image` ) VALUES ('"+c.getDuree()+"','"+c.getNom()+"','"+c.getIntensite()+"','"+c.getBienfaits()+"','"+c.getImage()+"')";
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        System.out.println("Cours ajouter avec sucée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
        
    }

    @Override
    public List<Cours> afficherCr() {
       List<Cours> cours = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `cours`";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Cours a = new Cours();
            a.setId(rs.getInt(1));
            a.setNom(rs.getString(2));
            a.setDuree(rs.getInt(3));
            a.setIntensite(rs.getString(4));
            a.setBienfaits(rs.getString(5));
            a.setImage(rs.getString(6));

            
            
            cours.add(a);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return cours;

    }

    @Override
    public void modiferCr(Cours c) {
      try {
            String querry = "UPDATE `cours` SET `id`='" + c.getId()+"', `nom`='" + c.getNom()+ "' , `intensite`='" + c.getIntensite()+ "', `bienfaits`='" + c.getBienfaits()+ "', `image`='" + c.getImage()+ "' WHERE id=" + c.getId();
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("Le cours est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
 }


    @Override
    public void supprimerCr(int id) {
       try {
            String querry = "DELETE FROM `cours` WHERE id="+id;
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("Cours est supprimée avec succée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

  

 
}   

