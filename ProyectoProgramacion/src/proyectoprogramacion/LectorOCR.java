/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;



/**
 *
 * @author dmatu
 */
public class LectorOCR {
    
    

    public LectorOCR() {
    }
    
    
    public void lectorPdf(String nombreArchivo)
    {
        try {
            File imagen = new File(nombreArchivo);
            Tesseract inst = new Tesseract();
            inst.setLanguage("spa");
            String resultado = inst.doOCR(imagen);
            this.escribirTextoOCR("textoOcr.txt", resultado);
        } catch (TesseractException ex) {
            Logger.getLogger(LectorOCR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void escribirTextoOCR(String nombreArchivo, String texto)
    {
        FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(nombreArchivo);
			//inherited method from java.io.OutputStreamWriter 
			fileWriter.write(texto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (fileWriter != null) {
					fileWriter.flush();
					fileWriter.close();					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    
    public void lectorPorRectangulos ()
    {
        try {
            File imagen = new File("image.png");
           
           
            
              List<AdapterRectangulo> lista = new ArrayList<AdapterRectangulo>();
            lista.addAll(ListaRectangulosSingleton.deseralizarListaRectangulos());
             
             
           
           for (int i = 0; i < lista.size(); i++) {
                 Tesseract inst = new Tesseract();
            inst.setLanguage("spa");
           
            
            int x = (int)lista.get(i).getPuntoX();
            int y = (int)lista.get(i).getPuntoY();
            int w = (int)lista.get(i).getAncho();
            int a = (int)lista.get(i).getAncho();
            
            String resultado = inst.doOCR(imagen);
              
          
                    
            System.out.println(lista.get(i).getID()+": "+ resultado);
            System.out.println("Punots: " + " X: "+ x + " Y: "+ y + " W: "+ w + " A: " + a );
          
            
            
            }
            
            
           
        } catch (TesseractException ex) {
            Logger.getLogger(LectorOCR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
