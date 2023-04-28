/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;




/**
 *
 * @author MSI
 */
public class Evenement {
    
 int id;
    String categorieEvenement;
     String nom_e;
     String date_e;
     String description_e;
     String lieu_e;
     int nbr_participants;
     String Etat;
     String image;  

     

    public Evenement() {
    }

    public Evenement(int id, String categorieEvenement, String nom_e, String date_e, String description_e, String lieu_e, int nbr_participants, String Etat, String image) {
        this.id = id;
        this.categorieEvenement = categorieEvenement;
        this.nom_e = nom_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.lieu_e = lieu_e;
        this.nbr_participants = nbr_participants;
        this.Etat = Etat;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", categorieEvenement=" + categorieEvenement + ", nom_e=" + nom_e + ", date_e=" + date_e + ", description_e=" + description_e + ", lieu_e=" + lieu_e + ", nbr_participants=" + nbr_participants + ", Etat=" + Etat + ", image=" + image + '}';
    }

    public Evenement(String categorieEvenement, String nom_e, String date_e, String description_e, String lieu_e, int nbr_participants, String Etat, String image) {
        this.categorieEvenement = categorieEvenement;
        this.nom_e = nom_e;
        this.date_e = date_e;
        this.description_e = description_e;
        this.lieu_e = lieu_e;
        this.nbr_participants = nbr_participants;
        this.Etat = Etat;
        this.image = image;
    }
    

    public int getId() {
        return id;
    }

    public String getCategorieEvenement() {
        return categorieEvenement;
    }

    public String getNom_e() {
        return nom_e;
    }

    public String getDate_e() {
        return date_e;
    }

    public String getDescription_e() {
        return description_e;
    }

    public String getLieu_e() {
        return lieu_e;
    }

    public int getNbr_participants() {
        return nbr_participants;
    }

    public String getEtat() {
        return Etat;
    }

    public String getImage() {
        return image;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setCategorieEvenement(String categorieEvenement) {
        this.categorieEvenement = categorieEvenement;
    }

    public void setNom_e(String nom_e) {
        this.nom_e = nom_e;
    }

    public void setDate_e(String date_e) {
        this.date_e = date_e;
    }

    public void setDescription_e(String description_e) {
        this.description_e = description_e;
    }

    public void setLieu_e(String lieu_e) {
        this.lieu_e = lieu_e;
    }

    public void setNbr_participants(int nbr_participants) {
        this.nbr_participants = nbr_participants;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public void setImage(String image) {
        this.image = image;
    }
 
    
     
}
