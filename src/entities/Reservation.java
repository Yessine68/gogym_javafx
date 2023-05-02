/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Amirov
 */
public class Reservation {
    private int id,cours_id ;
    private Date date ;
    private String type ;

    public Reservation() {
    }

    public Reservation(int cours_id, Date date, String type) {
        this.cours_id = cours_id;
        this.date = date;
        this.type = type;
    }

    public Reservation(int id, int cours_id, Date date, String type) {
        this.id = id;
        this.cours_id = cours_id;
        this.date = date;
        this.type = type;
    }

    

    public int getId() {
        return id;  
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCours_id() {
        return cours_id;
    }

    public void setCours_id(int cours_id) {
        this.cours_id = cours_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", cours_id=" + cours_id + ", date=" + date + ", type=" + type + '}';
    }
    
    
}
