package entities;

public class Produit {

  private int id ;
  private String nom_prod;
  private String description;
  private int prix;
  private int nbr_prods;
  private String categorie;
  private String image;


    public Produit(int id, String nom_prod, String description, int prix , int nbr_prods, String categorie, String image) {
        this.nom_prod = nom_prod;
        this.id = id;
        this.description = description;
        this.prix = prix;
        this.nbr_prods = nbr_prods;
        this.categorie = categorie;
        this.image = image;
    }

  public Produit() {
  }

    public Produit(String nom_prod, String description, int prix ,int nbr_prods,String categorie,String image) {
        this.nom_prod = nom_prod;
        this.description = description;
        this.prix = prix;
        this.nbr_prods = nbr_prods;
        this.categorie = categorie;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCAategorie(String categorie) {
        this.categorie = categorie;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbr_prods() {
        return nbr_prods;
    }

    public void setNbr_prods(int nbr_prods) {
        this.nbr_prods = nbr_prods;
    }

    @Override
    public String toString() {
        return "produit{" +
                "id=" + id +
                ", nom_prod='" + nom_prod + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", nbr_prods=" + nbr_prods +
                '}';
    }
}
