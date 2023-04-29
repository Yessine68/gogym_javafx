/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author HanaM
 */
public class Abonnement {
    
    private int id, prix_a;
    private String nom_a, type_a, description_a;
    private Date debut_a, fin_a;
    //private List<Salle> salles;
    private String salles;
    public Abonnement() {
    }

    public Abonnement(int id, String nom_a, String type_a, int prix_a, String description_a, Date debut_a, Date fin_a/*, List<Salle> salles*/) {
        this.id = id;
        this.nom_a = nom_a;
        this.type_a = type_a;
        this.prix_a = prix_a;
        this.description_a = description_a;
        this.debut_a = debut_a;
        this.fin_a = fin_a;
        //this.salles = salles;
    }
    
    public Abonnement(String nom_a, String type_a, int prix_a, String description_a, Date debut_a, Date fin_a) {
        this.nom_a = nom_a;
        this.type_a = type_a;
        this.prix_a = prix_a;
        this.description_a = description_a;
        this.debut_a = debut_a;
        this.fin_a = fin_a;
    }
public Abonnement(String nom_a, String type_a, int prix_a, String description_a, Date debut_a, Date fin_a,String salles) {
        this.nom_a = nom_a;
        this.type_a = type_a;
        this.prix_a = prix_a;
        this.description_a = description_a;
        this.debut_a = debut_a;
        this.fin_a = fin_a;
        this.salles = salles;
    }
    public int getId() {
        return id;
    }
    
    public String getNom_a() {
        return nom_a;
    }

    public String getType_a() {
        return type_a;
    }
    
    public int getPrix_a() {
        return prix_a;
    }

    public String getDescription_a() {
        return description_a;
    }

    public Date getDebut_a() {
        return debut_a;
    }

    public Date getFin_a() {
        return fin_a;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom_a(String nom_a) {
        this.nom_a = nom_a;
    }

    public void setType_a(String type_a) {
        this.type_a = type_a;
    }

    public void setPrix_a(int prix_a) {
        this.prix_a = prix_a;
    }

    public void setDescription_a(String description_a) {
        this.description_a = description_a;
    }

    public void setDebut_a(Date debut_a) {
        this.debut_a = debut_a;
    }

    public void setFin_a(Date fin_a) {
        this.fin_a = fin_a;
    }
    
    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", nom_a=" + nom_a + ", type_a=" + type_a + ", prix_a=" + prix_a + ", description_a=" + description_a + ", debut_a=" + debut_a + ", fin_a=" + fin_a + '}';
    }

    public String getSalles() {
        return salles;
    }

    public void setSalles(String salles) {
        this.salles = salles;
    }
      
}
