/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Serllet
 */
public class LectorOCR {
    
    
   
    
    
    public static void lectorSoloTexto(File file)
    {
        try {
            Tesseract instancia = new Tesseract();
            instancia.setLanguage("spa");
            instancia.setDatapath("tessdata"); // path to tessdata directory
            String result = instancia.doOCR(file);
            System.out.println(result);
            
            escribirResultado(result);
        } catch (TesseractException ex) {
            Logger.getLogger(LectorOCR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void escribirResultado(String resultado)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("textoOCR.txt");
            pw = new PrintWriter(fichero);

            pw.println(resultado);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    public static void lectorPorAreasRectangulares(Rectangle area, File file)
    {
        
        try {
            Tesseract instancia = new Tesseract();
             instancia.setLanguage("spa");
            instancia.setDatapath("tessdata");
           // path to tessdata directory
            String result = instancia.doOCR(file, area);
            System.out.println(result);
        } catch (TesseractException ex) {
            Logger.getLogger(LectorOCR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
    
}
