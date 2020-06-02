package proyectoprogramacion;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Serllet
 */
public class FXMLDibujarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static final String FILE_FORMAT = "png";

    private File destination = new File("image.png");
    private Color colorRectangulo;
    private int contadorRectangulo = 0;
    GraphicsContext gc;
    @FXML
    Canvas dibujo;
    Image imagenPDF;
    Rectangle rect = new Rectangle();
    SimpleDoubleProperty rectinitX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectinitY = new SimpleDoubleProperty();
    SimpleDoubleProperty rectX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectY = new SimpleDoubleProperty();

    @FXML
    private ImageView Atras;

    @FXML
    private Label CuadroTexto;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button Finalizar;
    @FXML
    private ImageView Deshacer2;
    @FXML
    private ImageView Rehacer2;
    @FXML
    private ImageView Eliminar2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gc = dibujo.getGraphicsContext2D();

    }

    @FXML
    void onAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLOrden.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            ((Stage)this.ap.getScene().getWindow()).close(); 
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLOrdenController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atras(MouseEvent event) throws IOException {
        if (event.getSource() == Atras) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLOrden.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                ((Stage)this.ap.getScene().getWindow()).close();
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLOrdenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void parametros(Image imge, Color colorR, String nombre) {
        imagenPDF = imge;
        CuadroTexto.setText(nombre);
        colorRectangulo = colorR;
        gc.drawImage(imagenPDF, 0, 0, 316, 468);
        //CUALES SON LAS DIMENSIONES DEL CANVAS?? 316 468?
        
    }

    @FXML
    public void eliminarCanvas(MouseEvent event) {
        //Eliminar dibujo
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            GraphicsContext gc = dibujo.getGraphicsContext2D();
            System.out.println(dibujo.getHeight());
            System.out.println(dibujo.getWidth());
            gc.clearRect(0, 0, dibujo.getWidth(), dibujo.getHeight());
            gc.drawImage(imagenPDF, 0, 0);
            contadorRectangulo--;
        }
    }

    @FXML
    public void handleMouse(MouseEvent event) {

        if (contadorRectangulo == 0) {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                rect.setX(event.getX());
                rect.setY(event.getY());
                rectinitX.set(event.getX());
                rectinitY.set(event.getY());
            } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                Double dx = event.getX() - rectinitX.getValue();
                Double dy = event.getY() - rectinitY.getValue();
                if (dx < 0) {
                    rectX.set(event.getX());
                    rect.setTranslateX(dx);
                    rect.widthProperty().bind(rectinitX.subtract(rectX));
                } else {
                    rectX.set(event.getX());
                    rect.setTranslateX(0);
                    rect.widthProperty().bind(rectX.subtract(rectinitX));
                }
                if (dy < 0) {
                    rectY.set(event.getY());
                    rect.setTranslateY(dy);
                    rect.heightProperty().bind(rectinitX.subtract(rectX));
                } else {
                    rectY.set(event.getY());
                    rect.setTranslateY(0);
                    rect.heightProperty().bind(rectY.subtract(rectinitY));
                }

            } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                //verificar que sea un solo rectangulo
                contadorRectangulo++;
                // color rectangulo
                gc.setStroke(colorRectangulo);
                gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                take(gc.getCanvas());
            }
        }
    }

    public void take(final Canvas canvas) {

        try {
            final WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            final WritableImage snapshot = canvas.snapshot(new SnapshotParameters(), writableImage);
            BufferedImage image = SwingFXUtils.fromFXImage(snapshot, null);
            ImageIO.write(image, "png", destination);
            System.out.println(destination.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(FXMLDibujarController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void onAction(MouseEvent event) {
    }

}
