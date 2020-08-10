
package otakus;

import java.awt.Font;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
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
    private ImageView RemoveButton;
    @FXML
    private Canvas canvas1;
    
     @FXML
    private Canvas canvas2;
    
    @FXML
    private Button Serializar;
    @FXML
    private Button cargarJSON;
    @FXML
    private ListView lista;
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        PDFImage = PDFCargador.cargarPDF();
        
         String texto = LectorOCR.leerTextoOCR();
         System.out.println("Texto: " + texto);
         GraphicsContext gc = canvas1.getGraphicsContext2D();
         gc.clearRect(0, 0, canvas1.getWidth(),canvas1.getHeight());
          gc.setTextAlign(TextAlignment.CENTER);
          
        gc.setTextBaseline(VPos.CENTER);
            gc.fillText(
            texto, 
            Math.round(canvas1.getWidth()  / 2), 
            Math.round(canvas1.getHeight() / 2),
            500
        );
     

        
        ListaRectangulosSingleton.getRectangulos().clear();
        refrescarCanvas();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Lista Rectangulos     
        lista.setCellFactory(param -> new ListCell<Rectangulo>() {
            private final ImageView imageView = new ImageView(new Image(this.getClass().getResource("color.png").toString()));            
            @Override
            public void updateItem(Rectangulo auxiliar, boolean empty){
                super.updateItem(auxiliar, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {                      
                    imageView.setEffect(new InnerShadow(100, auxiliar.getColor()));                    
                    setGraphic(imageView);                 
                    setText(" : "+auxiliar.getId() + "\n");                                
                }
            }
        });        
        this.refrescarDos();
    }    
    
    public void refrescarDos(){      
        ObservableList<Rectangulo> listaObservable = FXCollections.observableArrayList(ListaRectangulosSingleton.getRectangulos());
        lista.setItems(listaObservable);
        lista.refresh();        
    }
    
    @FXML
    public void handleHandle(MouseEvent event){ // el manejo del manejo de canvas XD
        Punto p = new Punto((int)event.getX(), (int)event.getY());
        if(clickBorrar){ //si la bandera esta activa el click es para borrar
            eliminarRectangulo(p);
        }
        else{ // si no es para crear un nuevo rectangulo
            if(p==null) return;
//            System.out.println(p.toString());
            handleCanvasClick(p);
            
        }
        clickBorrar=false;  //la bandera vuelve a ser falsa una vez que se utilizó el metodo de borrar
    }
    
    public void handleCanvasClick(Punto p){
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
                System.out.println("X1" + inicio.getX());
                  System.out.println("Y1" + inicio.getX());
                  System.out.println("X2" + fin.getX());
                  System.out.println("Y2" + fin.getY());
               
                String seleccion = JOptionPane.showInputDialog("Ingrese nombre para recuadro");
                while (esIdRepetida(seleccion) || seleccion.isEmpty()){
                        ventanaEmergenteMensaje("ID ya existente o no válida");
                        seleccion = JOptionPane.showInputDialog("Reingrese nombre para recuadro");
                    }                       
                r.setColorR(new Random().nextInt(255));
                r.setColorG(new Random().nextInt(255));
                r.setColorB(new Random().nextInt(255));
                r.setId(seleccion);
                agregarRectangulo(r);
                 Rectangle rectangulo = new Rectangle(inicio.getX(),inicio.getY(), (fin.getX() - inicio.getX()), (fin.getY()- inicio.getY()) );
                String resultado = LectorOCR.lectorPorAreasRectangulares(rectangulo, "documento.png");
                GraphicsContext gc = canvas2.getGraphicsContext2D();
                gc.fillText(
                seleccion, 
                Math.round(10), 
                Math.round(10),
                500
                );
                
                refrescarCanvas();       
                inicio = null;
                fin = null; 
            }   
    }
    
    public void agregarRectangulo(Rectangulo rParaAgregar){
        //Agrega un rectangulo a la lista si es valido
        //Se considera valido si su punto no esta dentro de otro rectangulo
        //De lo contrario son ignorados.
        if (!estaDentroDeLista(rParaAgregar.getInicio()) && !estaDentroDeLista(rParaAgregar.getFin())){
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
    
    public void refrescarCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setHeight(PDFImage.getHeight());
        canvas.setWidth(PDFImage.getWidth());
        gc.drawImage(PDFImage, 0, 0, canvas.getWidth(), canvas.getHeight());
    
//    Dibujar rectangulos

        for(Rectangulo r : ListaRectangulosSingleton.getRectangulos()){
            int ancho = r.getFin().getX() - r.getInicio().getX();
            int alto = r.getFin().getY() - r.getInicio().getY();
            gc.setStroke(r.getColor());
            gc.setLineWidth(2);
            gc.strokeRect(r.getInicio().getX(), r.getInicio().getY(), ancho, alto);
        } 
        this.refrescarDos();
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
    public void handlerSalirRectangulo(){
        inicio = null;
        fin = null;
    }
    
    @FXML
    public void handleRemove(ActionEvent event) { //onAction del boton borrar
//        System.out.println("entra? handle remove");
//        ArrayList<Rectangulo> lista = ListaRectangulosSingleton.getRectangulos();
//            if(lista.size()>0){
//                Rectangulo ultimo = lista.get(lista.size()-1);
//                ListaRectangulosSingleton.getRectangulos().remove(ultimo);
//                refrescarCanvas();
//        }

        clickBorrar=true;
    }

    public boolean estaDentro(Punto p, Rectangulo r){
//        System.out.println("veamos si esta adentro");
//            System.out.println(p.getX()+", "+p.getY()+ "son los puntos");
        if(r.getInicio().getX()<p.getX() && p.getX()<r.getFin().getX()){
//            System.out.println(p.getX()+", "+p.getY()+ "son los puntos");
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
    
    public boolean esIdRepetida (String ID){
        boolean validador = false;
        for(Rectangulo r : ListaRectangulosSingleton.getRectangulos()){
            if (ID.equals(r.getId())){
                validador = true;
            }
        }
        return validador;
    }
    
    public void eliminarRectangulo(Punto p){
        try {
            for(Rectangulo r : ListaRectangulosSingleton.getRectangulos()){
                if(estaDentro(p, r)){
//                    System.out.println("esta adentro "+estaDentro(p, r));
                    ListaRectangulosSingleton.getRectangulos().remove(r);
                }
            }
            refrescarCanvas();
        } catch (Exception e) {
            for(Rectangulo r : ListaRectangulosSingleton.getRectangulos()){
                if(estaDentro(p, r)){
//                    System.out.println("esta adentro "+estaDentro(p, r));
                    ListaRectangulosSingleton.getRectangulos().remove(r);
                }
            }
            refrescarCanvas();
        }
    }

    @FXML
    public void SerializarRectangulos(ActionEvent event) throws IOException {
        String nombreArchivo = JOptionPane.showInputDialog("Ingrese nombre para almacenar json");
        new JSONManagement().serializarListaRectangulos(nombreArchivo, ListaRectangulosSingleton.getRectangulos());
    }

    public void ventanaEmergente(int tipo, String mensaje, String titulo){    
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

    @FXML
    public void cargarPlantillaJSON(ActionEvent event) throws IOException {
        ListaRectangulosSingleton.setRectangulos(null);
//        ListaRectangulosSingleton.getRectangulos().clear();
        ListaRectangulosSingleton.getRectangulos().add(new JSONManagement().cargarJSON());
//        System.out.println(ListaRectangulosSingleton.getRectangulos().isEmpty());
//        ArrayList<Rectangulo> a = ListaRectangulosSingleton.getRectangulos();
//        System.out.println(a.get(0).getId());
        refrescarCanvas();
    }
 
    
}