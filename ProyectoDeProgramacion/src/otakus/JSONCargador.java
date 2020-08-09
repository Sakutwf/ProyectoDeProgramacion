/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author Saaku
 */
public class JSONCargador {
    /**
     * carga un json con un fileChooser y lo retorna como lista
     * @return 
     */
    public static void cargarJSON(){ //CAMBIAR A LISTA EN VEZ DE VOID Y VER COMO DIANTRES RECONOCEEL TIPO :(
        PDDocument document = new PDDocument();
        FileChooser escogerJSON = new FileChooser();
        escogerJSON.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo", "*Archivo"));
        File archivoJSON = escogerJSON.showOpenDialog(null);
        ArrayList<Rectangulo> listaCargadaJSON = new ArrayList<Rectangulo>();
        
        
        
            try{
                document = PDDocument.load(archivoJSON);
            }
            catch (IOException ex) {                
                System.out.print(ex.getMessage());
            }
    }
}
