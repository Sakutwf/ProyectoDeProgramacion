
package otakus;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Serllet y Escarlet
 */
public class FXMLDocumentController implements Initializable {
    private Image PDFImage;
//    private static ArrayList<Rectangulo> listadoRectangulos= new ArrayList<>();
    private Punto inicio;
    private Punto fin;
    
    @FXML
    private Label label;
    @FXML
    private Canvas canvas;
    @FXML
    private Button button;
    @FXML
    private Button RemoveButton;
    @FXML
    private Canvas canvas1;
    @FXML
    private Button Serializar;
    @FXML
    
    private void handleButtonAction(ActionEvent event) {
        PDFImage = PDFCargador.cargarPDF();
        ListaRectangulosSingleton.getRectangulos().clear();
        refrescarCanvas();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleCanvasClick(MouseEvent event){
        Punto p = new Punto((int)event.getX(), (int)event.getY());
        if(inicio == null){
            inicio = p;
        }
        else{
            fin = p;
            Rectangulo r = new Rectangulo(inicio, fin);
            agregarRectangulo(r);
            inicio = null;
            fin = null;
            refrescarCanvas();
        }
    }
    
    private void agregarRectangulo(Rectangulo r){
        //Agrega un rectangulo a la lista si es valido
        //Se considera valido si su punto de inicio esta a la izquierda dek fin
        //De lo contrario son ignorados.
        if(!(r.getAlto() <=0 )){
            if(!(r.getAncho()<=0 )){
                ListaRectangulosSingleton.getRectangulos().add(r);
            }
        }
    }
    
    private void refrescarCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(PDFImage, 0, 0, canvas.getWidth(), canvas.getHeight());
    
//    Dibujar rectangulos

        for(Rectangulo r : ListaRectangulosSingleton.getRectangulos()){
            int ancho = r.getFin().getX() - r.getInicio().getX();
            int alto = r.getFin().getY() - r.getInicio().getY();
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2);
            gc.strokeRect(r.getInicio().getX(), r.getInicio().getY(), ancho, alto);
        }    
    }

    @FXML
    private void handleRemove(ActionEvent event) {
            ArrayList<Rectangulo> lista = ListaRectangulosSingleton.getRectangulos();
        if(lista.size()>0){
            Rectangulo ultimo = lista.get(lista.size()-1);
            ListaRectangulosSingleton.getRectangulos().remove(ultimo);
            refrescarCanvas();
        }
       
    }

    @FXML
    private void SerializarRectangulos(ActionEvent event) throws IOException {
        ListaRectangulosSingleton.serializarListaRectangulos();
    }
    
}
