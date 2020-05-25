/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.PDFToImage;

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
        // TODO
    }

    void parametros(Image imge) {
        this.pdf= imge;
        PDF.setImage(imge);
    }

    @FXML
    void onAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDibujar.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLOrdenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void finalizarDibujo(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLMostrarPDF.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            FXMLDibujarController controlador = (FXMLDibujarController) fxmlLoader.getController();
            controlador.parametros(pdf);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLOrdenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
