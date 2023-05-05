/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MSI
 */
public class CategorieEvenement {
    int id  ; 
    String nom_cat_e ; 

    public CategorieEvenement() {
    }

    public CategorieEvenement(int id, String nom_cat_e) {
        this.id = id;
        this.nom_cat_e = nom_cat_e;
    }

    public CategorieEvenement(String nom_cat_e) {
        this.nom_cat_e = nom_cat_e;
    }

    public int getId() {
        return id;
    }

    public String getNom_cat_e() {
        return nom_cat_e;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_cat_e(String nom_cat_e) {
        this.nom_cat_e = nom_cat_e;
    }
    
    @Override
      public String toString() {
        return "CategorieEvenement{" + "id=" + id + ", nom_cat_e=" + nom_cat_e + '}';
    }
    
}
