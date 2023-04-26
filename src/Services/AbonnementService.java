/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;
import Entities.Abonnement;
import Entities.Salle;
import Utils.MyDB;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HanaM
 */
public class AbonnementService implements IService<Abonnement>{
    
    Connection cnx;

    public AbonnementService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    public void ajouter(Abonnement a,List<String> sallesChecked) throws SQLException {        
        String req = "INSERT INTO Abonnement (nom_a, type_a, prix_a, description_a, debut_a, fin_a) VALUES ('"+a.getNom_a()+"','"+a.getType_a()+"','"+a.getPrix_a()+"','"+a.getDescription_a()+"','"+a.getDebut_a()+"','"+a.getFin_a()+"') ";
        Statement st = cnx.createStatement();
        st.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        int idAbonnement=0;
        if (rs.next()) {
            idAbonnement = rs.getInt(1);
        }
        for (String nom :sallesChecked){
            String req2 = "SELECT id FROM salle WHERE nom_s = ?";
            PreparedStatement ss = cnx.prepareStatement(req2);
            ss.setString(1, nom);
            ResultSet rs2 = ss.executeQuery();
            while (rs2.next()){
                int id = rs2.getInt("id");
                String req3 = "INSERT INTO abonnementSalle (idabonnement, idsalle) VALUES ("+idAbonnement+","+id+")";
                st.executeUpdate(req3);
            }
        }
    }
    
    @Override
    public void ajouter(Abonnement a) throws SQLException {        
        String req = "INSERT INTO Abonnement (nom_a, type_a, prix_a, description_a, debut_a, fin_a) VALUES ('"+a.getNom_a()+"','"+a.getType_a()+"','"+a.getPrix_a()+"','"+a.getDescription_a()+"','"+a.getDebut_a()+"','"+a.getFin_a()+"') ";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    public void modifier(Abonnement a,List<String> sitesAdded,List<String> sitesDeleted) throws SQLException {
        String req = "UPDATE Abonnement SET nom_a = ?, type_a = ?, prix_a = ?, description_a = ?, debut_a = ?, fin_a = ? WHERE ID = ?";
        PreparedStatement as = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        as.setString(1, a.getNom_a());
        as.setString(2, a.getType_a());
        as.setInt(3, a.getPrix_a());
        as.setString(4, a.getDescription_a());
        as.setDate(5, a.getDebut_a());
        as.setDate(6, a.getFin_a());
        as.setInt(7, a.getId());
        as.executeUpdate();
        
        ResultSet rs = as.getGeneratedKeys();
    int idAbonnement = a.getId();

    String req2 = "SELECT id FROM salle WHERE nom_s = ?";
    PreparedStatement ps2 = cnx.prepareStatement(req2);

    for (String nom : sitesAdded) {
        ps2.setString(1, nom);
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            int idSalle = rs2.getInt("id");
            String req3 = "INSERT INTO abonnementSalle (idabonnement, idsalle) VALUES (?, ?)";
            PreparedStatement ps3 = cnx.prepareStatement(req3);
            ps3.setInt(1, idAbonnement);
            ps3.setInt(2, idSalle);
            ps3.executeUpdate();
        }
    }
    for (String nom : sitesDeleted) {
        ps2.setString(1, nom);
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            int idSalle = rs2.getInt("id");
            String req5 = "DELETE FROM abonnementsalle WHERE idabonnement = ? and idsalle = ?";
            PreparedStatement ss = cnx.prepareStatement(req5);
            ss.setInt(1, idAbonnement);
            ss.setInt(2, idSalle);
            ss.executeUpdate();
        }
    }
        
        
        
        
    }
    
    
    
    
    @Override
    public void modifier(Abonnement a) throws SQLException {
        String req = "UPDATE Abonnement SET nom_a = ?, type_a = ?, prix_a = ?, description_a = ?, debut_a = ?, fin_a = ? WHERE ID = ?";
        PreparedStatement as = cnx.prepareStatement(req);
        as.setString(1, a.getNom_a());
        as.setString(2, a.getType_a());
        as.setInt(3, a.getPrix_a());
        as.setString(4, a.getDescription_a());
        as.setDate(5, a.getDebut_a());
        as.setDate(6, a.getFin_a());
        as.setInt(7, a.getId());
        as.executeUpdate();
    }
    

    @Override
    public void supprimer(Abonnement a) throws SQLException {
        String req = "DELETE FROM Abonnement WHERE id = ?";
        PreparedStatement as = cnx.prepareStatement(req);
        as.setInt(1, a.getId());
        as.executeUpdate();
    }

    @Override
    public List<Abonnement> recuperer() throws SQLException {
        
        List<Abonnement> Abonnements = new ArrayList<>();
        
        String req = "SELECT * FROM Abonnement";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            Abonnement a = new Abonnement();
            String req2 = "SELECT s.nom_s FROM Abonnement a join abonnementsalle aas join salle s on a.id=aas.idabonnement and s.id=aas.idsalle  where a.id="+rs.getInt("id");
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(req2);
            String salles = "";
            while (rs2.next()){
                salles+=rs2.getString(1)+"/";
            }
            a.setSalles(salles);
            
            
            a.setId(rs.getInt("id"));
            a.setNom_a(rs.getString("nom_a"));
            a.setType_a(rs.getString("type_a"));
            a.setPrix_a(rs.getInt("prix_a"));
            a.setDescription_a(rs.getString("description_a"));
            a.setDebut_a(rs.getDate("debut_a"));
            a.setFin_a(rs.getDate("fin_a"));
            
            Abonnements.add(a);
        }
        
        return Abonnements;
    }
       
    public List<Abonnement> recupererById(int id) throws SQLException {
        
        List<Abonnement> Abonnements = new ArrayList<>();
        
        String req = "SELECT * FROM Abonnement WHERE id = ?";
        PreparedStatement as = cnx.prepareStatement(req);
        as.setInt(1, id);
        ResultSet rs = as.executeQuery();
        
        while(rs.next()){
            Abonnement a = new Abonnement();
            a.setId(rs.getInt("id"));
            a.setNom_a(rs.getString("nom_a"));
            a.setType_a(rs.getString("type_a"));
            a.setPrix_a(rs.getInt("prix_a"));
            a.setDescription_a(rs.getString("description_a"));
            a.setDebut_a(rs.getDate("debut_a"));
            a.setFin_a(rs.getDate("fin_a"));
            
            Abonnements.add(a);
        }
        
        return Abonnements;
    }

    public List<Abonnement> abonnementsSalle(Salle salle) throws SQLException {
        List<Abonnement> Abonnements = new ArrayList<>();
        
        String req = "SELECT a.id,a.nom_a,a.type_a,a.prix_a,a.description_a,a.debut_a,a.fin_a FROM Abonnement a join abonnementsalle aas join salle s on a.id=aas.idabonnement and s.id=aas.idsalle WHERE s.id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, salle.getId());
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            Abonnement a = new Abonnement();
            a.setId(rs.getInt("id"));
            a.setNom_a(rs.getString("nom_a"));
            a.setType_a(rs.getString("type_a"));
            a.setPrix_a(rs.getInt("prix_a"));
            a.setDescription_a(rs.getString("description_a"));
            a.setDebut_a(rs.getDate("debut_a"));
            a.setFin_a(rs.getDate("fin_a"));
            
            Abonnements.add(a);
        }
        
        return Abonnements;
    }

    public List<Salle> getSallesAbonnement(Abonnement newValue) throws SQLException {
        List<Salle> salles = new ArrayList<>();
        
        String req = "SELECT s.id , s.nom_s FROM Abonnement a join abonnementsalle aas join salle s on a.id=aas.idabonnement and s.id=aas.idsalle WHERE a.id = ?";
        PreparedStatement as = cnx.prepareStatement(req);
        as.setInt(1, newValue.getId());
        ResultSet rs = as.executeQuery();
        
        while(rs.next()){
            Salle s = new Salle();
            s.setId(rs.getInt(1));
            s.setNom_s(rs.getString(2));
            salles.add(s);
        }
        
        return salles;
    }

    public List<Abonnement> recupererSearchPar(String entry, String ch) throws SQLException {
        List<Abonnement> Abonnements = new ArrayList<>();
        
        String req = "SELECT * FROM Abonnement where "+ch+" like '%"+entry+"%'";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            Abonnement a = new Abonnement();
            
            String req2 = "SELECT s.nom_s FROM Abonnement a join abonnementsalle aas join salle s on a.id=aas.idabonnement and s.id=aas.idsalle  where a.id="+rs.getInt("id");
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(req2);
            String salles = "";
            while (rs2.next()){
                salles+=rs2.getString(1)+"/";
            }
            a.setSalles(salles);
            
            a.setId(rs.getInt("id"));
            a.setNom_a(rs.getString("nom_a"));
            a.setType_a(rs.getString("type_a"));
            a.setPrix_a(rs.getInt("prix_a"));
            a.setDescription_a(rs.getString("description_a"));
            a.setDebut_a(rs.getDate("debut_a"));
            a.setFin_a(rs.getDate("fin_a"));
            
            Abonnements.add(a);
        }
        
        return Abonnements;
    }
    
    public void toPDF(List<Abonnement> abonnements) throws DocumentException, SQLException {
        
            try {
                com.itextpdf.text.Document doc;
                doc = new Document();
                com.itextpdf.text.pdf.PdfWriter.getInstance(doc, new FileOutputStream("../../../Liste_des_Abonnements.pdf"));
                
                doc.open();
                
                Paragraph header = new Paragraph("Liste des abonnements et des salles associées", 
                                          FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.ORANGE));
                header.setAlignment(Element.ALIGN_CENTER);
                doc.add(header);
                doc.add(Chunk.NEWLINE);
                
                PdfPTable table = new PdfPTable(7);

                table.setWidthPercentage(100);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);

                // first row
                int i = 1;
                PdfPCell cell = new PdfPCell(new Phrase());
                cell = new PdfPCell(new Phrase("Nom "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Type "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Prix "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Description "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Salles "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                
                cell = new PdfPCell(new Phrase("Date Debut "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Date Fin "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                for (Abonnement a :abonnements){
                
                
                table.addCell(String.valueOf(a.getNom_a()));
                table.addCell(String.valueOf(a.getType_a()));
                table.addCell(String.valueOf(a.getPrix_a()));
                table.addCell(String.valueOf(a.getDescription_a()));
                table.addCell(String.valueOf(a.getSalles()));
                table.addCell(String.valueOf(a.getDebut_a()));
                table.addCell(String.valueOf(a.getFin_a()));
                /*
                List<Salle> salles = new ArrayList<Salle>();
                String req2 = "SELECT * FROM Abonnement a join abonnementsalle aas join salle s on a.id=aas.idabonnement and s.id=aas.idsalle  where a.id="+a.getId();
                Statement st2 = cnx.createStatement();
                ResultSet rs = st2.executeQuery(req2);
                while (rs.next()){
                    Salle s = new Salle();
                    s.setId(rs.getInt("s.id"));
                    s.setNom_s(rs.getString("s.nom_s"));
                    s.setEmail_s(rs.getString("s.email_s"));
                    s.setTel_s(rs.getInt("s.tel_s"));
                    s.setAdresse_s(rs.getString("s.adresse_s"));
                    s.setType(rs.getString("s.type_s"));
                    s.setVille_s(rs.getString("s.ville_s"));
                    s.setImage_s(rs.getString("s.image_s"));
                    s.setPerimetre_s(rs.getFloat("s.perimetre_s"));
                    s.setLike_s(rs.getInt("s.like_s"));
                    s.setLongitude_s(rs.getDouble("s.longitude_s"));
                    s.setLatitude_s(rs.getDouble("s.latitude_s"));
                    salles.add(s);
                }
                for (Salle salle : salles) {
                    Paragraph salleLine = new Paragraph(" - " + salle.getNom_s(), 
                                                         FontFactory.getFont(FontFactory.HELVETICA, 12));
                    salleLine.setAlignment(Element.ALIGN_LEFT);
                    doc.add(salleLine);
                }
                */
                
                
                
                
                i++;
                }
                
               
                
                
                doc.add(table); 
                
               
                doc.close();
                System.out.println("done pdf exporté");
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    
}
