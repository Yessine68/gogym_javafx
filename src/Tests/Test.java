/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Abonnement;
import Entities.Salle;
import Services.AbonnementService;
import Services.SalleService;
import Utils.MyDB;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author HanaM
 */
public class Test {
     
    public static void main(String[] args) {
        
        //MyDB db = MyDB.getInstance();

        AbonnementService as = new AbonnementService();
        
//Ajout        
//        Abonnement a = new Abonnement("Yellow", "Annuel", 1500, "Abonnement haut de gamme pour les utilisateurs exigeants", new Date(2023-1900, 04-1, 10), new Date(2024-1900, 04-1, 10));
//
//        try {
//            as.ajouter(a);
//            System.out.println("L'abonnement a été ajouté avec succès.");
//        } catch (SQLException ex) {
//            System.err.println("Erreur lors de l'ajout de l'abonnement : " + ex.getMessage());
//        }

//Modification
//        Abonnement a = new Abonnement (2, "Yellow", "annuel", 1200, "Abonnement haut de gamme pour les utilisateurs exigeants", new Date(2023-1900, 04-1, 10), new Date(2024-1900, 04-1, 10));
//        
//        try {
//            as.modifier(a);
//            System.out.println("L'abonnement a été modifié avec succès.");
//        } catch (SQLException ex) {
//            System.err.println("Erreur lors de la modification de l'abonnement : " + ex.getMessage());
//        }
    
//Récuperation All    
//        try {
//            System.out.println("Voici tous les abonnements : " + as.recuperer());
//        } catch (SQLException ex) {
//            System.err.println("Erreur lors de l'affichage des abonnements : " + ex.getMessage());
//        }
        
//Récuperation By Id 
//        int idRecupere = 2;
//        try {
//            System.out.println("Voici l'abonnement de l'id " + idRecupere + " : " + as.recupererById(idRecupere));
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
        
//Suppression
//        Abonnement a = new Abonnement();
//        a.setId(1);
//        try {
//            as.supprimer(a);
//            System.out.println("Abonnement supprimé avec succès.");
//        } catch (SQLException ex) {
//            System.err.println("Erreur lors de la suppression de l'abonnement :" + ex.getMessage());
//        }
        

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        SalleService ss = new SalleService();

//Ajout    
//        Salle s = new Salle ("Lac", "soukra@gogym.tn", 71789678, "Tunisia Mall", "Tunis", "lac.png", 1234, 1); 
//        
//        try {
//            ss.ajouter(s);
//            System.out.println("La salle a été ajouté avec succès.");
//        } catch (SQLException ex) {
//            System.err.println("Erreur lors de l'ajout de la salle : " + ex.getMessage());
//        }

//Modification
//        Salle s = new Salle (2, "Lac", "Lac@gogym.tn", 71789678, "Tunisia Mall", "Tunis", "lac.png", 1234, 1); 
//        
//        try {
//            ss.modifier(s);
//            System.out.println("La salle a été modifié avec succès.");
//        } catch (SQLException ex) {
//            System.err.println("Erreur lors de la modification de la salle : " + ex.getMessage());
//        }

//Récuperation All    
//        try {
//            System.out.println("Voici la liste de tous les salles : " + ss.recuperer());
//        } catch (SQLException ex) {
//            System.err.println("Erreur lors de l'affichage des salles : " + ex.getMessage());
//        }
        
//Récuperation By Id 
//        int idRecupere = 2;
//        try {
//            System.out.println("Voici la salle avec l'id " + idRecupere + " : " + ss.recupererById(idRecupere));
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }

//Suppression
//        Salle s = new Salle();
//        s.setId(1);
//        try {
//            ss.supprimer(s);
//            System.out.println("La salle a été supprimé avec succès.");
//        } catch (SQLException ex) {
//            System.err.println("Erreur lors de la suppression de la salle : " + ex.getMessage());
//        }

    }
    
}
