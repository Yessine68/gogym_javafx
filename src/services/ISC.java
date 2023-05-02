/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author Amirov
 */
public interface ISC<T> {
    public void ajouterCr(T c);
     public List<T> afficherCr();
     public void modiferCr(T c);
    public void supprimerCr(int id);
     
}