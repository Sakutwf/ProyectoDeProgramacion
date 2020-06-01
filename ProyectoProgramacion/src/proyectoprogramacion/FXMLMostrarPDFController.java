package proyectoprogramacion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Serllet
 */
public class FXMLMostrarPDFController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    private ImageView PDFmodificado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BufferedImage buffer = ImageIO.read(new File("image.png"));;
            Image imge = SwingFXUtils.toFXImage(buffer, null);
            this.PDFmodificado.setImage(imge);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMostrarPDFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
