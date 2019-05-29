package com.measurementapp_2.fileSupport;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.measurementapp_2.entity.Project;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class PdfSupport {

     private static String convertName(String name) {

         String[] tmp = name.split(" ");
         StringBuilder result = new StringBuilder("");
         for(String s : tmp) {

            result.append(s);
         }
         return result.toString();
     }



     public static String generate(Project project) {

         //String path = System.getProperty("user.dir") + "\\" +project.getName() + ".pdf";
        String name = convertName(project.getName());
        String path; //= System.getProperty("user.dir");
        //path = path.substring(0, path.length()-3) + name + ".pdf";
        path = "C:\\Users\\warjo\\PdfFolder\\" + name + ".pdf";

         try {
             Document document = new Document();
             PdfWriter.getInstance(document, new FileOutputStream(path));

             document.open();

             PdfPTable table1 = new PdfPTable(1);
             table1.addCell("Project name: " + project.getName());

             PdfPTable table2 = new PdfPTable(4);
             table2.addCell("NAME");
             table2.addCell("DESCRIPTION");
             table2.addCell("UNIT");
             table2.addCell("RESULT");
             project.getMeasurements().forEach(m -> {

                 table2.addCell(m.getName());
                 table2.addCell(m.getDescription());
                 table2.addCell(m.getUnit());
                 table2.addCell(m.getResult());
             });

             document.add(table1);
             document.add(table2);
             document.close();
         }
         catch (Exception e) {
             e.printStackTrace();
         }

         return path;
     }

     public static void download(HttpServletResponse response, String path) {

         File file = new File(path);

         try {
             response.setContentType("application/pdf");
             response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
             BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

             byte[] buffer = new byte[1024];
             int bytesRead = 0;
             while ((bytesRead = inStrem.read(buffer)) != -1) {
                 outStream.write(buffer, 0, bytesRead);
             }
             outStream.flush();
             inStrem.close();
         }
         catch (Exception e) {
             e.printStackTrace();
         }
     }


     public static void delete(String path) {

        try {
            File file = new File(path);
            file.delete();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
     }
}
