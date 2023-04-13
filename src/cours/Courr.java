/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours;

import entities.Cours;
import entities.Reservation;
import java.sql.Date;
import services.ServiceCr;
import services.ServiceRes;



/**
 *
 * @author Amirov
 */
public class Courr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ServiceCr sc = new ServiceCr() ;
        ServiceRes sr = new ServiceRes() ;
        
      //    Cours c = new Cours(22,"bib","moyenne","amek","dsq");
      //sc.ajouterCr(c);
     
     // sc.supprimerCr(6);
        
     
       // Reservation r = new Reservation(5, new Date(2022, 04, 04),"cours");
      //sr.ajouterRv(r);
        
     // Reservation reservation = new Reservation(4, 5, new Date(2022, 04, 04), "zz");
      // sr.modiferRv(reservation);
      //sr.modiferRv(4,5,new Date(2022, 4, 4),"zz"); 
        
         // System.out.println( sc.afficherCr().toString());
          System.out.println( sr.afficherRv().toString());


    }
    
}
