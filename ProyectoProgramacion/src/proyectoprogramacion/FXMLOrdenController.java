package proyectoprogramacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 * FXML Controller class
 *
 * @author Serllet
 */
public class FXMLOrdenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView PDF;

    @FXML
    private Button NombreC;

    @FXML
    private Button RutC;

    @FXML
    private Button EmailC;

    @FXML
    private Button NombreV;

    @FXML
    private Button RutV;

    @FXML
    private Button Total;

    @FXML
    private Button FinalizarO;

    @FXML
    private ImageView Deshacer;

    @FXML
    private ImageView Rehacer;

    @FXML
    private ImageView Eliminar;

    private Image pdf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BufferedImage buffer = ImageIO.read(new File("image.png"));;
            Image imge = SwingFXUtils.toFXImage(buffer, null);
            this.pdf = imge;
            PDF.setImage(pdf);
        } catch (IOException ex) {
            Logger.getLogger(FXMLOrdenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void parametros(Image imge) {
        this.pdf = imge;
        PDF.setImage(this.pdf);
    }

    @FXML
    void onAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDibujar.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            FXMLDibujarController controlador = (FXMLDibujarController) fxmlLoader.getController();

            // colores por boton
            if (event.getSource() == NombreC) {
                controlador.parametros(pdf, Color.PURPLE, "Nombre Cliente");
            } else if (event.getSource() == RutC) {
                controlador.parametros(pdf, Color.GREEN, "Rut Cliente");
            } else if (event.getSource() == EmailC) {
                controlador.parametros(pdf, Color.BLUE, "Email Cliente");
            } else if (event.getSource() == NombreV) {
                controlador.parametros(pdf, Color.RED, "Nombre vendedor");
            } else if (event.getSource() == RutV) {
                controlador.parametros(pdf, Color.BLACK, "Rut vendedor");
            } else if (event.getSource() == Total) {
                controlador.parametros(pdf, Color.ORANGE, "Total compra");
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLOrdenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void finalizarDibujo(ActionEvent event) {
        if (event.getSource() == FinalizarO) {
            guardarImagen();
            System.out.println("SSSS");
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLMostrarPDF.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            FXMLMostrarPDFController controlador = (FXMLMostrarPDFController) fxmlLoader.getController();
            //controlador.parametros(pdf);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLOrdenController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Guardar Imagen en PDF

    public void guardarImagen() {
        try (PDDocument document = new PDDocument()) {
            InputStream in = new FileInputStream("image.png");
            BufferedImage bimg = ImageIO.read(in);
            float width = bimg.getWidth();
            float height = bimg.getHeight();
            PDPage page = new PDPage(new PDRectangle(width, height));
            document.addPage(page);
            PDImageXObject pdImage = PDImageXObject.createFromFile("image.png", document);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(pdImage, 0, 0);
            contentStream.close();
            in.close();

            document.save("test.pdf");
        } catch (IOException ex) {
            Logger.getLogger(FXMLOrdenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
