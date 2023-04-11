package entities;

import javax.management.relation.Role;

public class User {

    private int id;
    private String username, password, email, nom, prenom, status, reset_token;
    private String[] roles;

    
    public User() {
    }

    public User(int id, String username, String password, String email, String nom, String prenom, String status, String reset_token, String[] roles) {
        this.id = id;
        
        this.username = username;
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.reset_token = reset_token;
        this.roles = roles;
    }

    public User(String username, String password, String email, String nom, String prenom, String status, String reset_token, String[] roles) {
   
        this.username = username;
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.reset_token = reset_token;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResetToken() {
        return reset_token;
    }

    public void setResetToken(String reset_token) {
        this.reset_token = reset_token;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

   

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + nom + '\'' +
                ", lastName='" + prenom + '\'' +
                ", status='" + status + '\'' +
                ", resetToken='" + reset_token + '\'' +
                ", roles=" + roles +
                '}';
    }
}
