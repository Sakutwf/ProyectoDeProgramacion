    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Serllet
 */
public class FXMLCargarPDFController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Button btnCargarPDF;

    @FXML
    void onActionHandle(ActionEvent event) {
            FileChooser escogerPDF = new FileChooser();
            escogerPDF.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File archivoPDF = escogerPDF.showOpenDialog(null);
            if (archivoPDF != null)
            {
                System.out.println("File: " + archivoPDF.getAbsolutePath());
            }
    }
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
