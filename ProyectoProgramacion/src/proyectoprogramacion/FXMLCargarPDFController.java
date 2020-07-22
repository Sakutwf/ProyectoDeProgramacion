package proyectoprogramacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 * FXML Controller class
 *
 * @author Serllet
 */
public class FXMLCargarPDFController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private PDDocument document = new PDDocument();
    private ImageView image = new ImageView();
    private Group root2 = new Group();
    private LectorOCR lo = new LectorOCR();
    @FXML
    private AnchorPane ap;
    @FXML
    private Button btnCargarPDF;

    @FXML
    void onActionHandle(ActionEvent event) {
        FileChooser escogerPDF = new FileChooser();
        escogerPDF.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File archivoPDF = escogerPDF.showOpenDialog(null);
        if (archivoPDF != null) {
            document.addPage(new PDPage());
            System.out.println("File: " + archivoPDF.getAbsolutePath());
            try {
                System.out.println(archivoPDF.getAbsoluteFile());
                lo.lectorPdf(archivoPDF.getAbsolutePath()); // lee el pdf
                document = PDDocument.load(archivoPDF); 
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLOrden.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                PDFRenderer pr = new PDFRenderer(document);
                BufferedImage img = pr.renderImage(0);
                
                // tamaño imagen despues de cargar
                System.out.println("CArgar: " + img.getHeight());
                System.out.println("CArgar: " + img.getWidth());

                File outputfile = new File("saved.png");
                ImageIO.write(img, "png", outputfile);
                document.close();
                Image imge = SwingFXUtils.toFXImage(img, null);

                image.setImage(imge);
                FXMLOrdenController controlador = (FXMLOrdenController) fxmlLoader.getController();
                controlador.parametros(imge);
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                ((Stage) this.ap.getScene().getWindow()).close();    ///Para cerrar userform            
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(ProyectoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
