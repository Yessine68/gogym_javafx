/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Cell;

/**
 *
 * @author MSI
 */
public class PDFevenement {
    public void liste_SallePDF(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException {

        EvenementService ec = new EvenementService();
        String message = "Voici la liste des evenements : \n\n";

       
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        Rectangle pageSize = new Rectangle(500, 500);
        document.open();
        
        Image img2 = Image.getInstance("C:\\Users\\don7a\\Documents\\GitHub\\gogym_javafx\\src\\images\\gogymlogo-removebg-preview.png");
        img2.scaleAbsolute(100, 100);
        document.add(img2);
        
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Evenement> evenements = ec.readAll();
        PdfPTable table = new PdfPTable(6);

        
        
        PdfPCell cl1 = new PdfPCell(new Phrase("Nom de l'evenement"));
        table.addCell(cl1);
        PdfPCell cl = new PdfPCell(new Phrase("Date de l'evenement"));
        table.addCell(cl);
        PdfPCell cl2 = new PdfPCell(new Phrase("Description"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("lieu"));
        table.addCell(cl3);
        PdfPCell cl4 = new PdfPCell(new Phrase("nombre de participation"));
        table.addCell(cl4);
        PdfPCell c15 = new PdfPCell(new Phrase("Image"));
        table.addCell(c15);
      
        table.setHeaderRows(1);
        document.add(table);
        

        int i = 0;
       for (i = 0; i < evenements.size(); i++) {
            
            table.addCell("" + evenements.get(i).getNom_e());
            table.addCell("" + evenements.get(i).getDate_e());
            table.addCell("" + evenements.get(i).getDescription_e());
            table.addCell("" + evenements.get(i).getLieu_e());
            table.addCell("" + evenements.get(i).getNbr_participants());
            Image img23 = Image.getInstance("C:\\Users\\don7a\\Documents\\GitHub\\gogym_javafx\\src\\uploads\\"+evenements.get(i).getImage());
            //img23.scaleAbsolute(100, 100);
            PdfPCell cell2 = new PdfPCell();
            cell2.addElement(img23);
            table.addCell(cell2);

        }
        document.add(table);

        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");


    }
}
