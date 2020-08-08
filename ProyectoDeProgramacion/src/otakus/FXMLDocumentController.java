
package otakus;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private boolean clickBorrar = false;
    
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
    private void handleHandle(MouseEvent event){ // el manejo del manejo de canvas XD
        Punto p = new Punto((int)event.getX(), (int)event.getY());
        if(clickBorrar){ //si la bandera esta activa el click es para borrar
            eliminarRectangulo(p);
        }
        else{ // si no es para crear un nuevo rectangulo
            if(p==null) return;
            System.out.println(p.toString());
            handleCanvasClick(p);
            
        }
        clickBorrar=false;  //la bandera vuelve a ser falsa una vez que se utilizó el metodo de borrar
    }
    
    private void handleCanvasClick(Punto p){
        //reviso que mi inicio este nulo, asi mi punto seleccionado será el inicial
            if(inicio == null){  
                inicio = p;
            }
            else{
                //si mi inicio no es nulo significa que ya saque el primer punto y ahora corresponde el segundo
                fin = p;
                // Para crear rectangulos con coordenadas negativas
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
                String seleccion = JOptionPane.showInputDialog("Ingrese nombre para recuadro");
                if(!seleccion.isEmpty()){
                    r.setTipo(seleccion);                          
                    agregarRectangulo(inicio, fin, r);
                    refrescarCanvas();       
                }
                inicio = null;
                fin = null; 
            }   
    }
    
     private void agregarRectangulo(Punto pIni, Punto pFin, Rectangulo rParaAgregar){
        //Agrega un rectangulo a la lista si es valido
        //Se considera valido si su punto no esta dentro de otro rectangulo
        //De lo contrario son ignorados.
        if (!estaDentroDeLista(pIni) && !estaDentroDeLista(pFin)){
                if (!nuevoRcontieneAlgunRectangulo(rParaAgregar)){
                    ListaRectangulosSingleton.getRectangulos().add(rParaAgregar);
                }
                else{
                    ventanaEmergenteMensaje("¡¡Ya existe un rectángulo dentro de esta posición!!");
                }
            }  
        else{
            ventanaEmergenteMensaje("¡¡Ya existe un rectángulo en esta posición!!");
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

//    @FXML
//    private void handleRemove(ActionEvent event) {
//        ArrayList<Rectangulo> lista = ListaRectangulosSingleton.getRectangulos();
//        if(lista.size()>0){
//            Rectangulo ultimo = lista.get(lista.size()-1);
//            ListaRectangulosSingleton.getRectangulos().remove(ultimo);
//            refrescarCanvas();
//        }
//       
//    }
    
    @FXML
    private void handlerSalirRectangulo(){
        inicio = null;
        fin = null;
    }
    
    @FXML
    private void handleRemove(ActionEvent event) { //onAction del boton borrar
        System.out.println("entra? handle remove");
//        ArrayList<Rectangulo> lista = ListaRectangulosSingleton.getRectangulos();
//            if(lista.size()>0){
//                Rectangulo ultimo = lista.get(lista.size()-1);
//                ListaRectangulosSingleton.getRectangulos().remove(ultimo);
//                refrescarCanvas();
//        }

        clickBorrar=true;
    }

    public boolean estaDentro(Punto p, Rectangulo r){
        System.out.println("veamos si esta adentro");
        if(r.getInicio().getX()<p.getX() && p.getX()<r.getFin().getX()){
            System.out.println(p.getX()+", "+p.getY()+ "son los puntos");
            return (r.getInicio().getY()<p.getY() && p.getY()<r.getFin().getY());
        }
        else{
            return false;
        }
    }
    
    public boolean estaDentroDeLista(Punto p){
        boolean estaDentro=false;
        for(Rectangulo r : ListaRectangulosSingleton.getRectangulos()){
            if(estaDentro(p, r)){
                estaDentro = true;
            }
        }
        return estaDentro;
    }
    
    public boolean nuevoRcontieneAlgunRectangulo(Rectangulo nuevoR){
        //Verifica si el rectangulo nuevo esta sobre algun rectangulo existente
        boolean validador = false;
        for(Rectangulo r : ListaRectangulosSingleton.getRectangulos()){
            if (estaDentro(r.getInicio(), nuevoR) 
                    || estaDentro(r.getFin(), nuevoR)) {
                validador = true;
            }
        }
        return validador;
    }
    
    private void eliminarRectangulo(Punto p){
        try {
            for(Rectangulo r : ListaRectangulosSingleton.getRectangulos()){
                if(estaDentro(p, r)){
                    System.out.println("esta adentro "+estaDentro(p, r));
                    ListaRectangulosSingleton.getRectangulos().remove(r);
                }
            }
            refrescarCanvas();
        } catch (Exception e) {
            for(Rectangulo r : ListaRectangulosSingleton.getRectangulos()){
                if(estaDentro(p, r)){
                    System.out.println("esta adentro "+estaDentro(p, r));
                    ListaRectangulosSingleton.getRectangulos().remove(r);
                }
            }
            refrescarCanvas();
        }
    }

    @FXML
    private void SerializarRectangulos(ActionEvent event) throws IOException {
        ListaRectangulosSingleton.serializarListaRectangulos();
    }
    
    public void ventanaEmergenteID(int tipo, String mensaje, String titulo){    
        //PARA AGREGAR ID A RECTANGULOS
        JOptionPane aux = new JOptionPane();
        aux.setMessage(mensaje);
        aux.setMessageType(tipo);       
        JDialog dialogo = aux.createDialog("Extract PDF by Las Otaku | "+titulo);  
        dialogo.setModal(true);
        dialogo.setAlwaysOnTop(true);
        dialogo.setVisible(true);            
    } 
    
    public void ventanaEmergenteMensaje(String mensaje){
        //PARA NOTIFICAR ALGUN MENSAJE
    Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("¡Alerta!");
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
    }
 
}