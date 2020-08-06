
package otakus;

import java.awt.HeadlessException;
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
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Serllet y Escarlet
 */
public class FXMLDocumentController implements Initializable {
    private Image PDFImage;
//    private static ArrayList<Rectangulo> listadoRectangulos= new ArrayList<>();
    private Punto inicio;
    private Punto fin;
    private boolean clickBorrar;
    
    @FXML
    private Label label;
    @FXML
    private Canvas canvas;
    @FXML
    private Button button;
    @FXML
    private Button RemoveButton;
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
            // AQUI VERIFICAR LAS WEAS DE PUNTOS NEGATIVOS UWU
            if(inicio.getX()>fin.getX() && inicio.getY()>fin.getY()){
                Punto aux = inicio;
                inicio = fin;
                fin = aux;
            }
            if(inicio.getX()>fin.getX() && inicio.getY()<fin.getY()){
                Punto nuevoInicio = new Punto(fin.getX(), inicio.getY());
                Punto nuevoFin = new Punto(inicio.getX(), fin.getY());
                inicio = nuevoInicio;
                fin = nuevoFin;
            }
            if(inicio.getX()<fin.getX() && inicio.getY()>fin.getY()){
                Punto nuevoInicio = new Punto(inicio.getX(), fin.getY());
                Punto nuevoFin = new Punto(fin.getX(), inicio.getY());
                inicio = nuevoInicio;
                fin = nuevoFin;
            }
            
            Rectangulo r = new Rectangulo(inicio, fin);
            inicio = null;
            fin = null;            
            String seleccion = JOptionPane.showInputDialog("Ingrese nombre para recuadro");
            if(!seleccion.isEmpty()){                         
                r.setTipo(seleccion);                          
                ListaRectangulosSingleton.getRectangulos().add(r);          
                refrescarCanvas();       
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
    
    public void ventanaEmergente(int tipo, String mensaje, String titulo){                    
        JOptionPane aux = new JOptionPane();
        aux.setMessage(mensaje);
        aux.setMessageType(tipo);       
        JDialog dialogo = aux.createDialog("Extract PDF by Las Otaku | "+titulo);  
        dialogo.setModal(true);
        dialogo.setAlwaysOnTop(true);
        dialogo.setVisible(true);            
    }
    
}