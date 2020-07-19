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
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Serllet
 * @author Escarlet
 */
public class FXMLDibujarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static final String FILE_FORMAT = "png";
    private File destination = new File("image.png");
    @FXML
    private Canvas dibujo;
    private Image imagenPDF;
    private int contadorRectangulo = 0;
    Rectangulo rect = new Rectangulo();
    
//    private Color colorRectangulo;
//    private String nombre;
//    GraphicsContext gc;
//    Rectangle rect = new Rectangle();
//    SimpleDoubleProperty rectinitX = new SimpleDoubleProperty();
//    SimpleDoubleProperty rectinitY = new SimpleDoubleProperty();
//    SimpleDoubleProperty rectX = new SimpleDoubleProperty();
//    SimpleDoubleProperty rectY = new SimpleDoubleProperty();
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
    @FXML
    private ImageView IMG_FONDO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rect.setGc(dibujo.getGraphicsContext2D());
    }

//    void onAction(ActionEvent event) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLOrden.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setResizable(false);
//            stage.setScene(new Scene(root1));
//            ((Stage)this.ap.getScene().getWindow()).close(); 
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLOrdenController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    void parametros(Image imge, Color colorR, String nombre) {
        imagenPDF = imge; 
        CuadroTexto.setText(nombre);
        rect.setColorRectangulo(colorR);
        rect.getGc().drawImage(imagenPDF, 0, 0, 316, 468);
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       

    @FXML
    public void eliminarCanvas(MouseEvent event) {  //Deberia funcionar con el actualizar del nacho 
        int ultimoRectanguloAgregado = ListaRectangulosSingleton.getRectangulos() == null? 0:
                ListaRectangulosSingleton.getRectangulos().size()-1;
        
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            GraphicsContext gc = dibujo.getGraphicsContext2D();
            System.out.println(dibujo.getHeight()); 
            System.out.println(dibujo.getWidth());
            gc.clearRect(0, 0, dibujo.getWidth(), dibujo.getHeight());
            gc.drawImage(imagenPDF, 0, 0);
            contadorRectangulo--;
//            for (int i = 0; i < ListaRectangulosSingleton.getRectangulos().size(); i++) {
//                //necesito forma de identificarlo
//            }
        }
        //Eliminar dibujo
        if (ListaRectangulosSingleton.getRectangulos().size() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("I have a great message for you!");
            alert.showAndWait();
            
            DibujarRectangulo(event);
        }
        else{
            ListaRectangulosSingleton.getRectangulos().remove(ListaRectangulosSingleton.getRectangulos().size()-1);
            DibujarRectangulo(event);
        }
        //Agrego la lista de rectangulos con el rectangulo eliminado al undo
        ListaRectangulosSingleton.getUndo().add(ListaRectangulosSingleton.getRectangulos());
    }

    @FXML
    public void handleMouse(MouseEvent event) {
        DibujarRectangulo(event);
    }
    public void DibujarRectangulo (MouseEvent event){
        if (contadorRectangulo == 0) {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                rect.setX(event.getX());
                rect.setY(event.getY());
                rect.rectinitX.set(event.getX());
                rect.rectinitY.set(event.getY());
            } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                Double dx = event.getX() - rect.rectinitX.getValue();
                Double dy = event.getY() - rect.rectinitY.getValue();
                if (dx < 0) {
                    rect.rectX.set(event.getX());
                    rect.setTranslateX(dx);
                    rect.widthProperty().bind(rect.rectinitX.subtract(rect.rectX));
                } else {
                    rect.rectX.set(event.getX());
                    rect.setTranslateX(0);
                    rect.widthProperty().bind(rect.rectX.subtract(rect.rectinitX));
                }
                if (dy < 0) {
                    rect.rectY.set(event.getY());
                    rect.setTranslateY(dy);
                    rect.heightProperty().bind(rect.rectinitX.subtract(rect.rectX));
                } else {
                    rect.rectY.set(event.getY());
                    rect.setTranslateY(0);
                    rect.heightProperty().bind(rect.rectY.subtract(rect.rectinitY));
                }

            } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                //verificar que sea un solo rectangulo
                contadorRectangulo++;
                // color rectangulo
                rect.gc.setStroke(rect.getColorRectangulo());
                rect.gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                //Se le asigna una id al rectangulo
                rect.setDato(CuadroTexto.getText());
                //Se agrega el rectangulo a la lista
                ListaRectangulosSingleton.getRectangulos().add(rect);
                ListaRectangulosSingleton.getUndo().add(ListaRectangulosSingleton.getRectangulos());
                for (int i = 0; i < ListaRectangulosSingleton.getRectangulos().size(); i++) {
                    System.out.println(ListaRectangulosSingleton.getRectangulos().get(i).getId());
                }
                int aux = ListaRectangulosSingleton.getRectangulos() == null? 0:
                ListaRectangulosSingleton.getRectangulos().size()-1;
                take(ListaRectangulosSingleton.getRectangulos().get(aux).gc.getCanvas());
                
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

    @FXML
    private void onAction(MouseEvent event) {
    }

    @FXML
    private void Undo(MouseEvent event) {
        ListaRectangulosSingleton.deshacer();
    }

    @FXML
    private void Redo(MouseEvent event) {
        ListaRectangulosSingleton.rehacer();
    }

    @FXML
    private void Finalizar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLOrden.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            ((Stage)this.ap.getScene().getWindow()).close(); 
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLOrdenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
