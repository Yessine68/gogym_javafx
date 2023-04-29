/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Abonnement;
import java.util.List;
import entities.Salle;
import utils.MyDB;
import utils.Variables;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
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

public class SalleService implements IService<Salle> {
    
    Connection cnx;

    public SalleService() {
        cnx = MyDB.getInstance().getCnx();
    }
    public void ajouter(Salle s,List<String> abonnementsChecked) throws SQLException {
        String req = "INSERT INTO Salle (nom_s, email_s, tel_s, adresse_s, ville_s, image_s, perimetre_s, like_s,type_s,longitude_s,latitude_s) VALUES ('"+s.getNom_s()+"','"+s.getEmail_s()+"',"+s.getTel_s()+",'"+s.getAdresse_s()+"','"+s.getVille_s()+"','"+s.getImage_s()+"',"+s.getPerimetre_s()+","+s.getLike_s()+",'"+s.getType()+"',"+s.getLongitude_s()+","+s.getLatitude_s()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        int idSalle=0;
        if (rs.next()) {
            idSalle = rs.getInt(1);
        }
        for (String nom :abonnementsChecked){
            String req2 = "SELECT id FROM Abonnement WHERE nom_a = ?";
            PreparedStatement ss = cnx.prepareStatement(req2);
            ss.setString(1, nom);
            
            ResultSet rs2 = ss.executeQuery();
            while (rs2.next()){
                int id = rs2.getInt("id");
                System.out.println(id);
                String req3 = "INSERT INTO abonnementSalle (idabonnement, idsalle) VALUES ("+id+","+idSalle+")";

                st.executeUpdate(req3);
            }
            
            
        }
        
        
    }
    @Override
    public void ajouter(Salle s) throws SQLException {
        String req = "INSERT INTO Salle (nom_s, email_s, tel_s, adresse_s, ville_s, image_s, perimetre_s, like_s,type_s) VALUES ('"+s.getNom_s()+"','"+s.getEmail_s()+"',"+s.getTel_s()+",'"+s.getAdresse_s()+"','"+s.getVille_s()+"','"+s.getImage_s()+"',"+s.getPerimetre_s()+","+s.getLike_s()+",'"+s.getType()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

       public void modifier(Salle s, List<String> abonnementsAdded, List<String> abonnementsDeleted) throws SQLException {
    String req = "UPDATE Salle SET nom_s = ?, email_s = ?, tel_s = ?, adresse_s = ?, ville_s = ?, image_s = ?, perimetre_s = ?, like_s = ?, type_s = ?, longitude_s = ?, latitude_s = ? WHERE ID = ?";
    PreparedStatement ps = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
    ps.setString(1, s.getNom_s());
    ps.setString(2, s.getEmail_s());
    ps.setInt(3, s.getTel_s());
    ps.setString(4, s.getAdresse_s());
    ps.setString(5, s.getVille_s());
    ps.setString(6, s.getImage_s());
    ps.setFloat(7, s.getPerimetre_s());
    ps.setInt(8, s.getLike_s());
    ps.setString(9, s.getType());
    ps.setDouble(10, s.getLongitude_s());
    ps.setDouble(11, s.getLatitude_s());
    ps.setInt(12, s.getId());
    ps.executeUpdate();

    ResultSet rs = ps.getGeneratedKeys();
    int idSalle = s.getId();

    String req2 = "SELECT id FROM Abonnement WHERE nom_a = ?";
    PreparedStatement ps2 = cnx.prepareStatement(req2);

    for (String nom : abonnementsAdded) {
        ps2.setString(1, nom);
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            int idAbonnement = rs2.getInt("id");
            String req3 = "INSERT INTO abonnementSalle (idabonnement, idsalle) VALUES (?, ?)";
            PreparedStatement ps3 = cnx.prepareStatement(req3);
            ps3.setInt(1, idAbonnement);
            ps3.setInt(2, idSalle);
            ps3.executeUpdate();
        }
    }
    for (String nom : abonnementsDeleted) {
        ps2.setString(1, nom);
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            int idAbonnement = rs2.getInt("id");
            String req5 = "DELETE FROM abonnementsalle WHERE idabonnement = ? and idsalle = ?";
            PreparedStatement ss = cnx.prepareStatement(req5);
            ss.setInt(1, idAbonnement);
            ss.setInt(2, Variables.salleDetail.getId());
            ss.executeUpdate();
        }
    }
        
    
}
        
        
        
    @Override
    public void modifier(Salle s) throws SQLException {
        String req = "UPDATE Salle SET nom_s = ?, email_s = ?, tel_s = ?, adresse_s = ?, ville_s = ?, image_s = ?, perimetre_s = ?, like_s = ?,type_s=? ,longitude_s=?,latitude_s=? WHERE ID = ?";
        PreparedStatement ss = cnx.prepareStatement(req);
        ss.setString(1, s.getNom_s());
        ss.setString(2, s.getEmail_s());
        ss.setInt(3, s.getTel_s());
        ss.setString(4, s.getAdresse_s());
        ss.setString(5, s.getVille_s());
        

        ss.setString(6, s.getImage_s());
        ss.setFloat(7, s.getPerimetre_s());
        ss.setInt(8, s.getLike_s());
        ss.setString(9,s.getType());
        ss.setDouble(10, s.getLongitude_s());
        ss.setDouble(11, s.getLatitude_s());
        ss.setInt(12, s.getId());
        ss.executeUpdate();
        
        
        
        
        
    }

    @Override
    public void supprimer(Salle s) throws SQLException {
        String req = "DELETE FROM Salle WHERE id = ?";
        PreparedStatement ss = cnx.prepareStatement(req);
        ss.setInt(1, s.getId());
        ss.executeUpdate();
    }

    @Override
    public List<Salle> recuperer() throws SQLException {
        
        List<Salle> Salles = new ArrayList<>();
        
        String req = "SELECT * FROM Salle";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            Salle s = new Salle();
            
            String req2 = "SELECT a.nom_a FROM Abonnement a join abonnementsalle aas join salle s on a.id=aas.idabonnement and s.id=aas.idsalle  where s.id="+rs.getInt("id");
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(req2);
            String abonnements = "";
            while (rs2.next()){
                abonnements+=rs2.getString(1)+"/";
            }
            s.setAbonnements(abonnements);
            
            s.setId(rs.getInt("id"));
            s.setNom_s(rs.getString("nom_s"));
            s.setEmail_s(rs.getString("email_s"));
            s.setTel_s(rs.getInt("tel_s"));
            s.setAdresse_s(rs.getString("adresse_s"));
            s.setType(rs.getString("type_s"));
            s.setVille_s(rs.getString("ville_s"));
            s.setImage_s(rs.getString("image_s"));
            s.setPerimetre_s(rs.getFloat("perimetre_s"));
            s.setLike_s(rs.getInt("like_s"));
            s.setLongitude_s(rs.getDouble("longitude_s"));
            s.setLatitude_s(rs.getDouble("latitude_s"));
            
            Salles.add(s);
        }
        
        return Salles;
    }
       
    public List<Salle> recupererById(int id) throws SQLException {
        
        List<Salle> Salles = new ArrayList<>();
        
        String req = "SELECT * FROM Salle WHERE id = ?";
        PreparedStatement ss = cnx.prepareStatement(req);
        ss.setInt(1, id);
        ResultSet rs = ss.executeQuery();
        
        while(rs.next()){
            Salle s = new Salle();
            s.setId(rs.getInt("id"));
            s.setNom_s(rs.getString("nom_s"));
            s.setEmail_s(rs.getString("email_s"));
            s.setTel_s(rs.getInt("tel_s"));
            s.setAdresse_s(rs.getString("adresse_s"));
            s.setType(rs.getString("type_s"));

            s.setVille_s(rs.getString("ville_s"));
            s.setImage_s(rs.getString("image_s"));
            s.setPerimetre_s(rs.getFloat("perimetre_s"));
            s.setLike_s(rs.getInt("like_s"));
            
            Salles.add(s);
        }
        
        return Salles;
    }

    public List<Abonnement> getAbonnementsSalle() throws SQLException {
        List<Abonnement> Abonnements = new ArrayList<>();
        
        String req = "SELECT a.id FROM Abonnement a join abonnementsalle aas join salle s on a.id=aas.idabonnement and s.id=aas.idsalle WHERE s.id = ?";
        PreparedStatement as = cnx.prepareStatement(req);
        as.setInt(1, Variables.salleDetail.getId());
        ResultSet rs = as.executeQuery();
        
        while(rs.next()){
            Abonnement a = new Abonnement();
            a.setId(rs.getInt(1));
            Abonnements.add(a);
        }
        
        return Abonnements;
    }

    public List<Salle> recupererSearchPar(String entry ,String ch) throws SQLException {
        
        List<Salle> Salles = new ArrayList<>();
        
        String req = "SELECT * FROM Salle where "+ch+" like '%"+entry+"%'";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            Salle s = new Salle();
            
            String req2 = "SELECT a.nom_a FROM Abonnement a join abonnementsalle aas join salle s on a.id=aas.idabonnement and s.id=aas.idsalle  where s.id="+rs.getInt("id");
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(req2);
            String abonnements = "";
            while (rs2.next()){
                abonnements+=rs2.getString(1)+"/";
            }
            s.setAbonnements(abonnements);
            
            s.setId(rs.getInt("id"));
            s.setNom_s(rs.getString("nom_s"));
            s.setEmail_s(rs.getString("email_s"));
            s.setTel_s(rs.getInt("tel_s"));
            s.setAdresse_s(rs.getString("adresse_s"));
            s.setType(rs.getString("type_s"));
            s.setVille_s(rs.getString("ville_s"));
            s.setImage_s(rs.getString("image_s"));
            s.setPerimetre_s(rs.getFloat("perimetre_s"));
            s.setLike_s(rs.getInt("like_s"));
            s.setLongitude_s(rs.getDouble("longitude_s"));
            s.setLatitude_s(rs.getDouble("latitude_s"));
            Salles.add(s);
        }
        
        return Salles;
    }

    public void toPDF(List<Salle> salles) throws DocumentException, SQLException {
        
            try {
                com.itextpdf.text.Document doc;
                doc = new Document();
                doc.setPageSize(PageSize.A4.rotate());
                com.itextpdf.text.pdf.PdfWriter.getInstance(doc, new FileOutputStream("../Liste_des_Salles.pdf"));
                
                doc.open();
                
                Paragraph header = new Paragraph("Liste des salles et des abonnements associées", 
                                          FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.ORANGE));
                header.setAlignment(Element.ALIGN_CENTER);
                doc.add(header);
                doc.add(Chunk.NEWLINE);
                //Test PDF
                PdfPTable table = new PdfPTable(11);

                table.setWidthPercentage(100);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);

                // first row
                int i = 1;
                PdfPCell cell = new PdfPCell(new Phrase());
                
                cell = new PdfPCell(new Phrase("Nom"));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Email"));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Téléphone"));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Adresse"));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Ville "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Image "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Périmetre "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Likes "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Les abonnements "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Longitude "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Latitude "));
                cell.setBackgroundColor(new BaseColor(239,250,230));
                table.addCell(cell);
                for (Salle s :salles){
                
                String img =s.getImage_s();
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("./src/Images/Salles/"+img);
                image.scaleToFit(5f, 5f);
                
                table.addCell(String.valueOf(s.getNom_s()));
                table.addCell(String.valueOf(s.getEmail_s()));
                table.addCell(String.valueOf(s.getTel_s()));
                table.addCell(String.valueOf(s.getAdresse_s()));
                table.addCell(String.valueOf(s.getVille_s()));
                table.addCell(image);
                table.addCell(String.valueOf(s.getPerimetre_s()));
                table.addCell(String.valueOf(s.getLike_s()));
                table.addCell(String.valueOf(s.getAbonnements()));
                table.addCell(String.valueOf(s.getLongitude_s()));
                table.addCell(String.valueOf(s.getLatitude_s()));
                
                
                i++;
                }
                
               
                
                
                doc.add(table); 
                
               
                doc.close();
                System.out.println("done");
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
}