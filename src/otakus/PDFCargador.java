package otakus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDFCargador {

    /**
     * carga un pdf con un fileChooser y lo retorna como Image
     *
     * @return
     */
    public static Image cargarPDF() {
        PDDocument document = new PDDocument();
        FileChooser escogerPDF = new FileChooser();
        escogerPDF.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File archivoPDF = escogerPDF.showOpenDialog(null);

        Image fxImage = null;
        BufferedImage img;
        if (archivoPDF != null) {

            document.addPage(new PDPage());
            try {
                document = PDDocument.load(archivoPDF);
                PDFRenderer pr = new PDFRenderer(document);

                img = pr.renderImageWithDPI(0, 150);
                ImageIOUtil.writeImage(img, "documento.png", 150);

                File imageFile = new File("documento.png");
                LectorOCR.lectorSoloTexto(imageFile);

                fxImage = SwingFXUtils.toFXImage(img, null);
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
                //Logger.getLogger(ProyectoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fxImage;
    }
}
