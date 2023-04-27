package services;
import Tools.MyConnexion;
import com.mysql.jdbc.log.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.Produit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;


public class produit_service {
   
    Connection cnx;
    public produit_service() {
        cnx = MyConnexion.getInstance().getCnx();
    }
   categorie_Service service = new categorie_Service();

    public void add_produit (Produit produit ) {
        try {
            String sql = "insert into produit(categorie_id,nom_prod,description,prix,nbr_prods,image)"
                    + "values (?,?,?,?,?,?)";
             PreparedStatement ste = cnx.prepareStatement(sql);


            categorie cat = service.readByNom(produit.getCategorie());
            ste.setInt(1, cat.getId());
            ste.setString(2, produit.getNom_prod());
            ste.setString(3, produit.getDescription());
            ste.setInt(4, produit.getPrix());
            ste.setInt(5, produit.getNbr_prods());
            ste.setString(6, produit.getImage());
            ste.executeUpdate();
        } catch (SQLException ex) {
                       System.err.println("Erreur lors de l'ajout  : " + ex.getMessage());
        }
    }
    public ObservableList<Produit> readAll() {
        ObservableList<Produit> produits = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM produit";
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom_prod");
                String desc = resultSet.getString("description");
                int prix = resultSet.getInt("prix");
                int nbr_prods = resultSet.getInt("nbr_prods");
                int catid = resultSet.getInt("categorie_id");
                categorie categorie = service.readById(catid);
                String cat = categorie.getNom();
                String img = resultSet.getString("image");
                Produit produit = new Produit(id, nom, desc,prix,nbr_prods,cat,img);
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

   public void delete(int e) {
        try {

            String deleteQuery = "DELETE FROM produit WHERE id = ?";
            PreparedStatement deleteStatement = cnx.prepareStatement(deleteQuery);
            deleteStatement.setInt(1,e);
            deleteStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la suppression  : " + ex.getMessage());
        }
    }

    public void update( Produit produit) {

        String query = "UPDATE produit SET nom_prod = ?, description = ?,prix = ?, nbr_prods=? ,categorie_id=? WHERE id = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setString(1, produit.getNom_prod());
            stmt.setString(2, produit.getDescription());
            stmt.setInt(3, produit.getPrix());
            stmt.setInt(4, produit.getNbr_prods());
            //stmt.setString(5, produit.getImage());
            String cat = produit.getCategorie();
            categorie categorie = service.readByNom(cat);
            stmt.setInt(5, categorie.getId());
            stmt.setInt(6, produit.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

public List<Produit> recherche(String nomRes ) {
        List<Produit> list = new ArrayList<>();
          try {
            String req = "Select * from produit";
            PreparedStatement statement = cnx.prepareStatement(req);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            int id = rs.getInt("id");
            int catid = rs.getInt("categorie_id");
                categorie categorie = service.readById(catid);
                String cat = categorie.getNom();
               String nom = rs.getString("nom_prod");
            String desc = rs.getString("description");
            int prix = rs.getInt("prix");
            int nbr_prods = rs.getInt("nbr_prods");
            String img = rs.getString("image");
            Produit p = new Produit(id, nom, desc,prix,nbr_prods,cat,img);
            list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        list=list.stream().filter(e -> e.getNom_prod().contains(nomRes) || e.getCategorie().contains(nomRes)).collect(Collectors.toList());
        return list;
    }
     
     public List<Produit> Trie(String sortOrder , List<Produit> list) {
       if(sortOrder=="ASC"){        
       list = list.stream()
			.sorted(Comparator.comparingInt(Produit::getPrix))
			.collect(Collectors.toList());
       }
       else{    
           list = list.stream()
			.sorted(Comparator.comparingInt(Produit::getPrix)
                        .reversed())
			.collect(Collectors.toList());
       }
 
           
               
        return list;
    }

  
public ObservableList<PieChart.Data> stat(){

        ObservableList<PieChart.Data> data=FXCollections.observableArrayList();

     try{
           String query ="SELECT COUNT(p.nom_Prod) AS nbre, c.nom\n" +
           "FROM produit p\n" +
           "LEFT JOIN categorie c ON p.categorie_id = c.id\n" +
              "GROUP BY p.categorie_id";

           PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            ResultSet  rs = PreparedStatement.executeQuery();
             while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("nom"),rs.getInt("nbre")));
            }  
             
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }        
    
    
    
    
    return data;
    
    
    
}
    public int hasUserRatedProduct(int id_prod, int id_user) throws SQLException {
        String query = "SELECT * FROM produitrating WHERE id_prod = ? AND id_user = ? ";
        PreparedStatement ste = cnx.prepareStatement(query);
        ste.setInt(1, id_prod);
        ste.setInt(2, id_user);
        ResultSet  rs = ste.executeQuery();
        if (rs.next()){               
            return rs.getInt("rate");
            }else
            return 0;
    }

    public void addRating(int id_prod, int id_user, int rate) throws SQLException {
        String query = "INSERT INTO produitrating (id_prod, id_user, rate) VALUES (?, ?, ?)";
        PreparedStatement ste = cnx.prepareStatement(query);
        try  {
            ste.setInt(1, id_prod);
            ste.setInt(2, id_user);
            ste.setInt(3, rate);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(produit_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public List<Produit> getProdsbyRating() throws SQLException {
        List<Produit> list = new ArrayList<>();
        String query = "SELECT AVG(rate),id_prod,produit.nom_prod,produit.image,produit.categorie_id,produit.description,produit.prix,produit.nbr_prods FROM `produitrating` left JOIN produit on id_prod=produit.id GROUP BY id_prod ORDER BY rate DESC LIMIT 5;";
        PreparedStatement ste = cnx.prepareStatement(query);
        
            ResultSet  rs = ste.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id_prod");
            int catid = rs.getInt("categorie_id");
                categorie categorie = service.readById(catid);
                String cat = categorie.getNom();
               String nom = rs.getString("nom_prod");
            String desc = rs.getString("description");
            int prix = rs.getInt("prix");
            int nbr_prods = rs.getInt("nbr_prods");
            String img = rs.getString("image");
            Produit p = new Produit(id, nom, desc,prix,nbr_prods,cat,img);
            list.add(p);
            }
        return list;
    }

}



        