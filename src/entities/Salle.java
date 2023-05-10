/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HanaM
 */
public class Salle {
    
    private int id, tel_s, like_s;
    private String nom_s, email_s, adresse_s, ville_s ,image_s;
    private float perimetre_s;
    private double longitude_s,latitude_s;
    private String abonnements;
    public Salle() {
    }

    public Salle(int id, String nom_s, String email_s, int tel_s, String adresse_s, String ville_s, String image_s, float perimetre_s, int like_s,String abonnements) {
        this.id = id;
        this.nom_s = nom_s;
        this.email_s = email_s;
        this.tel_s = tel_s;
        this.adresse_s = adresse_s;
        this.ville_s = ville_s;
        this.image_s = image_s;
        this.perimetre_s = perimetre_s;
        this.like_s = like_s;
        this.abonnements=abonnements;
    }

    public Salle(String nom_s, String email_s, int tel_s, String adresse_s, String ville_s, String image_s, float perimetre_s, int like_s, double longitude_s, double latitude_s) {
        this.nom_s = nom_s;
        this.email_s = email_s;
        this.tel_s = tel_s;
        this.adresse_s = adresse_s;
        this.ville_s = ville_s;
        this.image_s = image_s;
        this.perimetre_s = perimetre_s;
        this.like_s = like_s;
        this.longitude_s=longitude_s;
        this.latitude_s=latitude_s;
    }
    public Salle(String nom_s, String email_s, int tel_s, String adresse_s, String ville_s, String image_s, float perimetre_s, int like_s, double longitude_s,double latitude_s,String abonnements) {
        this.nom_s = nom_s;
        this.email_s = email_s;
        this.tel_s = tel_s;
        this.adresse_s = adresse_s;
        this.ville_s = ville_s;
        this.image_s = image_s;
        this.perimetre_s = perimetre_s;
        this.like_s = like_s;
        this.longitude_s=longitude_s;
        this.latitude_s=latitude_s;
        this.abonnements=abonnements;
    } 
    public int getId() {
        return id;
    }
    
    public String getNom_s() {
        return nom_s;
    }

    public String getEmail_s() {
        return email_s;
    }

    public int getTel_s() {
        return tel_s;
    }
        
    public String getAdresse_s() {
        return adresse_s;
    }

    public String getVille_s() {
        return ville_s;
    }

    public String getImage_s() {
        return image_s;
    }

    public float getPerimetre_s() {
        return perimetre_s;
    }
    
    public int getLike_s() {
        return like_s;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_s(String nom_s) {
        this.nom_s = nom_s;
    }

    public void setEmail_s(String email_s) {
        this.email_s = email_s;
    }
    
    public void setTel_s(int tel_s) {
        this.tel_s = tel_s;
    }

    public void setAdresse_s(String adresse_s) {
        this.adresse_s = adresse_s;
    }

    public void setVille_s(String ville_s) {
        this.ville_s = ville_s;
    }

    public void setImage_s(String image_s) {
        this.image_s = image_s;
    }

    public void setPerimetre_s(float perimetre_s) {
        this.perimetre_s = perimetre_s;
    }
    
    public void setLike_s(int like_s) {
        this.like_s = like_s;
    }

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", tel_s=" + tel_s + ", like_s=" + like_s + ", nom_s=" + nom_s + ", email_s=" + email_s + ", adresse_s=" + adresse_s + ", ville_s=" + ville_s + ", image_s=" + image_s + ", perimetre_s=" + perimetre_s + ", longitude_s=" + longitude_s + ", latitude_s=" + latitude_s + ", abonnements=" + abonnements + '}';
    }
    
    public Salle(int id, int tel_s, int like_s, String nom_s, String email_s, String adresse_s, String ville_s, String image_s, float perimetre_s) {
        this.id = id;
        this.tel_s = tel_s;
        this.like_s = like_s;
        this.nom_s = nom_s;
        this.email_s = email_s;
        this.adresse_s = adresse_s;
        this.ville_s = ville_s;
        this.image_s = image_s;
        this.perimetre_s = perimetre_s;
    }

    public Salle(int tel_s, int like_s, String nom_s, String email_s, String adresse_s, String ville_s, String image_s, float perimetre_s) {
        this.tel_s = tel_s;
        this.like_s = like_s;
        this.nom_s = nom_s;
        this.email_s = email_s;
        this.adresse_s = adresse_s;
        this.ville_s = ville_s;
        this.image_s = image_s;
        this.perimetre_s = perimetre_s;
    }

    public double getLongitude_s() {
        return longitude_s;
    }

    public void setLongitude_s(double longitude_s) {
        this.longitude_s = longitude_s;
    }

    public double getLatitude_s() {
        return latitude_s;
    }

    public void setLatitude_s(double latitude_s) {
        this.latitude_s = latitude_s;
    }

    public String getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(String abonnements) {
        this.abonnements = abonnements;
    }
    
}
