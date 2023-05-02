/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Amirov
 */
public class Cours {
    private int id, duree ;
    private String nom,	intensite,bienfaits,image ;

    public Cours() {
    }

    public Cours(int duree, String nom, String intensite, String bienfaits, String image) {
        this.duree = duree;
        this.nom = nom;
        this.intensite = intensite;
        this.bienfaits = bienfaits;
        this.image = image;
    }

    public Cours(int id, int duree, String nom, String intensite, String bienfaits, String image) {
        this.id = id;
        this.duree = duree;
        this.nom = nom;
        this.intensite = intensite;
        this.bienfaits = bienfaits;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIntensite() {
        return intensite;
    }

    public void setIntensite(String intensite) {
        this.intensite = intensite;
    }

    public String getBienfaits() {
        return bienfaits;
    }

    public void setBienfaits(String bienfaits) {
        this.bienfaits = bienfaits;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", duree=" + duree + ", nom=" + nom + ", intensite=" + intensite + ", bienfaits=" + bienfaits + ", image=" + image + '}';
    }
    
    
    
    
}
