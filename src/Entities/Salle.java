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
public class Salle {
    
    private int id, tel_s, like_s;
    private String nom_s, email_s, adresse_s, ville_s ,image_s;
    private float perimetre_s;

    public Salle() {
    }

    public Salle(int id, String nom_s, String email_s, int tel_s, String adresse_s, String ville_s, String image_s, float perimetre_s, int like_s) {
        this.id = id;
        this.nom_s = nom_s;
        this.email_s = email_s;
        this.tel_s = tel_s;
        this.adresse_s = adresse_s;
        this.ville_s = ville_s;
        this.image_s = image_s;
        this.perimetre_s = perimetre_s;
        this.like_s = like_s;

    }

    public Salle(String nom_s, String email_s, int tel_s, String adresse_s, String ville_s, String image_s, float perimetre_s, int like_s) {
        this.nom_s = nom_s;
        this.email_s = email_s;
        this.tel_s = tel_s;
        this.adresse_s = adresse_s;
        this.ville_s = ville_s;
        this.image_s = image_s;
        this.perimetre_s = perimetre_s;
        this.like_s = like_s;
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
        return "Salle{" + "id=" + id + ", nom_s=" + nom_s + ", email_s=" + email_s + ", tel_s=" + tel_s + ", adresse_s=" + adresse_s + ", ville_s=" + ville_s + ", image_s=" + image_s + ", perimetre_s=" + perimetre_s + ", like_s=" + like_s + '}';
    }
    
}
