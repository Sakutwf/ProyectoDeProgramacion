/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Serllet
 */
public class FXMLDibujarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Color colorRectangulo;
    @FXML
    Canvas dibujo;

    Rectangle rect = new Rectangle();
    SimpleDoubleProperty rectinitX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectinitY = new SimpleDoubleProperty();
    SimpleDoubleProperty rectX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectY = new SimpleDoubleProperty();

    @FXML
    private Button Finalizar;

    @FXML
    private ImageView Atras;

    @FXML
    private Label CuadroTexto;

    @FXML
    private ImageView Deshacer2;

    @FXML
    private ImageView Rehacer2;

    @FXML
    private ImageView Eliminar2;

    @FXML
    private ImageView PDFDibujo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void onAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLOrden.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLOrdenController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    void parametros(Image imge, Color colorR) {
        PDFDibujo.setImage(imge);
        colorRectangulo = colorR;
    }

    @FXML
    public void handleMouse(MouseEvent event) {
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
            Rectangle r = getNewRectangle();

            GraphicsContext gc = dibujo.getGraphicsContext2D();
            // 
            gc.setStroke(colorRectangulo);
            gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        }
    }

    private Rectangle getNewRectangle() {
        Rectangle r = new Rectangle();
        return r;
    }   
}
