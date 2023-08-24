/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author ahmed
 */
import entities.Evenement;
import gui.EventDetailController;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.util.Properties;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;



public class JavaMailEvent {

   public void sendmail(File qrCodeFile,String code,String mail) throws IOException {    
    String host="ssl0.ovh.net";  
  final String user="gogym@mega.tn";//change accordingly  
  final String password="ahmed1234";//change accordingly  
    
  String to=mail;//change accordingly  
  
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true");  
   
   /////////////////////

  
     
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });
   
 
        
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("Confirmation de participation");  
     
     
    String content = new String("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
"<head>\n" +
"    <!--[if gte mso 9]>\n" +
"    <xml>\n" +
"        <o:OfficeDocumentSettings>\n" +
"            <o:AllowPNG/>\n" +
"            <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
"        </o:OfficeDocumentSettings>\n" +
"    </xml>\n" +
"    <![endif]-->\n" +
"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <meta name=\"x-apple-disable-message-reformatting\">\n" +
"    <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n" +
"    <title></title>\n" +
"\n" +
"    <style type=\"text/css\">\n" +
"        table, td { color: #ff0000; } a { color: #161a39; text-decoration: underline; }\n" +
"        @media only screen and (min-width: 620px) {\n" +
"            .u-row {\n" +
"                width: 600px !important;\n" +
"            }\n" +
"            .u-row .u-col {\n" +
"                vertical-align: top;\n" +
"            }\n" +
"\n" +
"            .u-row .u-col-100 {\n" +
"                width: 600px !important;\n" +
"            }\n" +
"\n" +
"        }\n" +
"\n" +
"        @media (max-width: 620px) {\n" +
"            .u-row-container {\n" +
"                max-width: 100% !important;\n" +
"                padding-left: 0px !important;\n" +
"                padding-right: 0px !important;\n" +
"            }\n" +
"            .u-row .u-col {\n" +
"                min-width: 320px !important;\n" +
"                max-width: 100% !important;\n" +
"                display: block !important;\n" +
"            }\n" +
"            .u-row {\n" +
"                width: calc(100% - 40px) !important;\n" +
"            }\n" +
"            .u-col {\n" +
"                width: 100% !important;\n" +
"            }\n" +
"            .u-col > div {\n" +
"                margin: 0 auto;\n" +
"            }\n" +
"        }\n" +
"        body {\n" +
"            margin: 0;\n" +
"            padding: 0;\n" +
"        }\n" +
"\n" +
"        table,\n" +
"        tr,\n" +
"        td {\n" +
"            vertical-align: top;\n" +
"            border-collapse: collapse;\n" +
"        }\n" +
"\n" +
"        p {\n" +
"            margin: 0;\n" +
"        }\n" +
"\n" +
"        .ie-container table,\n" +
"        .mso-container table {\n" +
"            table-layout: fixed;\n" +
"        }\n" +
"\n" +
"        * {\n" +
"            line-height: inherit;\n" +
"        }\n" +
"\n" +
"        a[x-apple-data-detectors='true'] {\n" +
"            color: inherit !important;\n" +
"            text-decoration: none !important;\n" +
"        }\n" +
"\n" +
"    </style>\n" +
"\n" +
"\n" +
"\n" +
"    <!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Lato:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
"\n" +
"</head>\n" +
"\n" +
"<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #f9f9f9;color: #ff0000\">\n" +
"<!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
"<!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
"<table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #f9f9f9;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"    <tbody>\n" +
"    <tr style=\"vertical-align: top\">\n" +
"        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
"            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
"\n" +
"\n" +
"            <div class=\"u-row-container\" style=\"padding: 0px;background-color: #f9f9f9\">\n" +
"                <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f9f9f9;\">\n" +
"                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #f9f9f9;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
"\n" +
"                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
"                        <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"                            <div style=\"width: 100% !important;\">\n" +
"                                <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
"\n" +
"                                    <table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                        <tbody>\n" +
"                                        <tr>\n" +
"                                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:15px;font-family:'Lato',sans-serif;\" align=\"left\">\n" +
"\n" +
"                                                <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #f9f9f9;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
"                                                    <tbody>\n" +
"                                                    <tr style=\"vertical-align: top\">\n" +
"                                                        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
"                                                            <span>&#160;</span>\n" +
"                                                        </td>\n" +
"                                                    </tr>\n" +
"                                                    </tbody>\n" +
"                                                </table>\n" +
"\n" +
"                                            </td>\n" +
"                                        </tr>\n" +
"                                        </tbody>\n" +
"                                    </table>\n" +
"\n" +
"                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <!--[if (mso)|(IE)]></td><![endif]-->\n" +
"                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"                    </div>\n" +
"                </div>\n" +
"            </div>\n" +
"\n" +
"\n" +
"\n" +
"            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"                <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
"                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
"\n" +
"                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
"                        <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"                            <div style=\"width: 100% !important;\">\n" +
"                                <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
"\n" +
"                                    <table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                        <tbody>\n" +
"                                        <tr>\n" +
"                                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:25px 10px;font-family:'Lato',sans-serif;\" align=\"left\">\n" +
"\n" +
"                                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
"                                                    <tr>\n" +
"                                                        <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
"\n" +
"                                                            <img align=\"center\" border=\"0\" src='https://i.ibb.co/2777d2T/unnamed.jpg' alt=\"Image\" title=\"Image\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 29%;max-width: 168.2px;\" width=\"168.2\"/>\n" +
"\n" +
"                                                        </td>\n" +
"                                                    </tr>\n" +
"                                                </table>\n" +
"\n" +
"                                            </td>\n" +
"                                        </tr>\n" +
"                                        </tbody>\n" +
"                                    </table>\n" +
"\n" +
"                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <!--[if (mso)|(IE)]></td><![endif]-->\n" +
"                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"                    </div>\n" +
"                </div>\n" +
"            </div>\n" +
"\n" +
"\n" +
"\n" +
"            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"                <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #161a39;\">\n" +
"                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #161a39;\"><![endif]-->\n" +
"\n" +
"                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"background-color: #df1818;width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
"                        <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"                            <div style=\"background-color: #df1818;width: 100% !important;\">\n" +
"                                <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
"\n" +
"                                    <table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                        <tbody>\n" +
"                                        <tr>\n" +
"                                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:37px 10px 10px;font-family:'Lato',sans-serif;\" align=\"left\">\n" +
"\n" +
"                                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
"                                                    <tr>\n" +
"                                                        <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
"\n" +
"\n" +
"                                                        </td>\n" +
"                                                    </tr>\n" +
"                                                </table>\n" +
"\n" +
"                                            </td>\n" +
"                                        </tr>\n" +
"                                        </tbody>\n" +
"                                    </table>\n" +
"\n" +
"                                    <table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                        <tbody>\n" +
"                                        <tr>\n" +
"                                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 10px 30px;font-family:'Lato',sans-serif;\" align=\"left\">\n" +
"\n" +
"                                                <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
"\n" +
"                                            </td>\n" +
"                                        </tr>\n" +
"                                        </tbody>\n" +
"                                    </table>\n" +
"\n" +
"                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <!--[if (mso)|(IE)]></td><![endif]-->\n" +
"                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"                    </div>\n" +
"                </div>\n" +
"            </div>\n" +
"\n" +
"\n" +
"\n" +
"            <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"                <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
"                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
"\n" +
"                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
"                        <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"                            <div style=\"width: 100% !important;\">\n" +
"                                <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
"\n" +
"                                    <table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                        <tbody>\n" +
"                                        <tr>\n" +
"                                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 40px 30px;font-family:'Lato',sans-serif;\" align=\"left\">\n" +
"\n" +
"                                                <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
"                                                    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">Dear Participant,</span></p>\n" +
"                                                    <p style=\"font-size: 14px; line-height: 140%;\">&nbsp;</p>\n" +
"                                                    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">We are pleased to inform you that your participation has been confirmed.</span></p>\n" +
            
"                                                    <p style=\"font-size: 14px; line-height: 140%;\">&nbsp;</p>\n" +
"                                                    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">Your seat is reserved, and you can look forward to an unforgettable experience.</span></p>\n" +
"                                                </div>\n" +
"\n" +
"                                            </td>\n" +
"                                        </tr>\n" +
"                                        </tbody>\n" +
"                                    </table>\n" +
"\n" +
"                                    <table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                        <tbody>\n" +
"                                        <tr>\n" +
"                                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px 40px;font-family:'Lato',sans-serif;\" align=\"left\">\n" +
"\n" +
"                                                <div align=\"left\">\n" +
"                                                    <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;font-family:'Lato',sans-serif;\"><tr><td style=\"font-family:'Lato',sans-serif;\" align=\"left\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"\" style=\"height:52px; v-text-anchor:middle; width:205px;\" arcsize=\"2%\" stroke=\"f\" fillcolor=\"#df2c1b\"><w:anchorlock/><center style=\"color:#FFFFFF;font-family:'Lato',sans-serif;\"><![endif]-->\n" +
"                                                    </a>\n" +
    "                                                    <img src=\\\"data:image/png;base64,\" + base64Image + \"\\\" width=\\\"100\\\" height=\\\"100\\\"/>                                                </div>\n" +
"\n" +
"                                                <!--[if mso]></center></v:roundrect></td></tr></table><![endif]-->\n" +
"                                                </div>\n" +
"\n" +
"                                            </td>\n" +
"                                        </tr>\n" +
"                                        </tbody>\n" +
"                                    </table>\n" +
"\n" +
"                                    <table style=\"font-family:'Lato',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                        <tbody>\n" +
"                                        <tr>\n" +
"                                            <td style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 40px 30px;font-family:'Lato',sans-serif;\" align=\"left\">\n" +
"\n" +
"                                                <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
"                                                    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"color: #888888; font-size: 14px; line-height: 19.6px;\"><em><span style=\"font-size: 16px; line-height: 22.4px;\">NB: You can scan the qrCode bellow to have your Participation code !</span></em></span><br /><span style=\"color: #888888; font-size: 14px; line-height: 19.6px;\"><em><span style=\"font-size: 16px; line-height: 22.4px;\">&nbsp;</span></em></span></p>\n" +
"                                                    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"color: #888888; font-size: 14px; line-height: 19.6px;\"><em><span style=\"font-size: 16px; line-height: 22.4px;\"> Please feel free to contact us if you have any further questions or concerns.</span></em></span><br /><span style=\"color: #888888; font-size: 14px; line-height: 19.6px;\"><em><span style=\"font-size: 16px; line-height: 22.4px;\">&nbsp;</span></em></span></p>\n" +
"                                                </div>\n" +
"\n" +
"                                            </td>\n" +
"                                        </tr>\n" +
"                                        </tbody>\n" +
"                                    </table>\n" +
"\n" +
"                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <!--[if (mso)|(IE)]></td><![endif]-->\n" +
"                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"                    </div>\n" +
"                </div>\n" +
"            </div>\n" +
"\n" +
"\n" +
"\n" +
"            <div class=\"u-row-container\" style=\"padding: 0px;background-color: #f9f9f9\">\n" +
"                <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #1c103b;\">\n" +
"                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"                        <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #f9f9f9;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #1c103b;\"><![endif]-->\n" +
"\n" +
"                        <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
"                        <div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"                            <div style=\"width: 100% !important;\">\n" +
"                                <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
"\n" +
"                                    <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        <!--[if (mso)|(IE)]></td><![endif]-->\n" +
"                        <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"                    </div>\n" +
"                </div>\n" +
"            </div>\n" +
"\n" +
"\n" +
"            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
"        </td>\n" +
"    </tr>\n" +
"    </tbody>\n" +
"</table>\n" +
"<!--[if mso]></div><![endif]-->\n" +
"<!--[if IE]></div><![endif]-->\n" +
"</body>\n" +
"\n" +
"</html>");
    //message.setText("This is simple program of sending email using JavaMail API");  
  // message.setContent(content, "text/html");
    /////
    MimeBodyPart textPart = new MimeBodyPart();
   // message.setContent(content, "text/html");
    textPart.setContent(content, "text/html");

    MimeBodyPart qrCodePart = new MimeBodyPart();
    qrCodePart.attachFile(qrCodeFile);
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(textPart);
    multipart.addBodyPart(qrCodePart);
    message.setContent(multipart);
    //send the message  
     Transport.send(message);  
  
     System.out.println("message sent successfully...");  
   
     } catch (MessagingException e) 
     {
         e.printStackTrace();
     }  
   }
}