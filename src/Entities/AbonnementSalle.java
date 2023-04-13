/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author HanaM
 */
public class AbonnementSalle {
    private int id;
    private Abonnement abonnement;
    private Salle salle;

    public AbonnementSalle() {
    }

    public AbonnementSalle(int id, Abonnement abonnement, Salle salle) {
        this.id = id;
        this.abonnement = abonnement;
        this.salle = salle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
