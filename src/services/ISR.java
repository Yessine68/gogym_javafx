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
public interface ISR <T> {
    public void ajouterRv(T r);
     public List<T> afficherRv();
     public void modiferRv(T r);
    public void supprimerRv(int id);
}
