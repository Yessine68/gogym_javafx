/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import services.ServiceCr;



/**
 *
 * @author Amirov
 */
public class pdf {
    
    
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        ServiceCr m=new ServiceCr();
        List<Cours> list=m.afficherCr();    
        document.add(new Paragraph("La liste des cours :"));
        document.add(new Paragraph("     "));
         for(Cours u:list)
        {
            
        document.add(new Paragraph("Id :"+u.getId()));
        document.add(new Paragraph("nom:"+u.getNom()));
        document.add(new Paragraph("duree :"+u.getDuree()));
                document.add(new Paragraph("intensite :"+u.getIntensite()));
                document.add(new Paragraph("bienfaits :"+u.getBienfaits()));
                document.add(new Paragraph("image :"+u.getImage()));

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
       
   
    
}