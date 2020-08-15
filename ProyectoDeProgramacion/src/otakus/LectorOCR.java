/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Serllet
 */
public class LectorOCR {

    public static void lectorSoloTexto(File file) {
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

    public static void escribirResultado(String resultado) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("textoOCR.txt");
            pw = new PrintWriter(fichero);

            pw.println(resultado);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    
    /**
     * Extrae información utilizando el motor OCR, utilizando un rectagulo
     * @param area rectangulo del area de interes.
     * @param fileName nombre de archivo de imagen.
     */
    public static String lectorPorAreasRectangulares(Rectangle area,String fileName ) {

        try {
            File file = new File(fileName);
            Tesseract instancia = new Tesseract();
            instancia.setLanguage("spa");
            instancia.setDatapath("tessdata");
            return instancia.doOCR(file, area);
           
        } catch (TesseractException ex) {
            Logger.getLogger(LectorOCR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String leerTextoOCR() {
        String texto = "";
        try {
            File myObj = new File("textoOCR.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                texto += data + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LectorOCR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texto;
    }

}
