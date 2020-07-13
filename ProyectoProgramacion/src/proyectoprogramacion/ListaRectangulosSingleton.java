/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Saaku
 */
public class ListaRectangulosSingleton {
    public static ArrayList<Rectangulo> listaDeRectangulos = null;
    
    public static ArrayList<Rectangulo> getRectangulos(){
        if(ListaRectangulosSingleton.listaDeRectangulos == null){
            ListaRectangulosSingleton.listaDeRectangulos = new ArrayList<Rectangulo>();
        }
        return ListaRectangulosSingleton.listaDeRectangulos;
    }
    
//   Aqui para Undo
    
    
    public static ArrayList<ArrayList<Rectangulo>> undo = null;
    
    public static ArrayList<ArrayList<Rectangulo>> getUndo(){
        if(ListaRectangulosSingleton.undo == null){
            ListaRectangulosSingleton.undo = new ArrayList<ArrayList<Rectangulo>>();
        }
        return ListaRectangulosSingleton.undo;
    }
    
    public static void deshacer(){
        ArrayList<Rectangulo> listaAuxiliar = new ArrayList<Rectangulo>();
        int ultimoElemento = ListaRectangulosSingleton.getUndo() == null? 0 : 
                ListaRectangulosSingleton.getUndo().size()-1;

        if (ListaRectangulosSingleton.getUndo() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("I have a great message for you!");
            alert.showAndWait();
        }
        else{
            for (int i = 0; i < (ListaRectangulosSingleton.getUndo().get(ultimoElemento).size()-1); i++) {
            listaAuxiliar.add(ListaRectangulosSingleton.getUndo().get(ultimoElemento).get(i));
            }
            ListaRectangulosSingleton.getRedo().add(ListaRectangulosSingleton.getUndo().get(ultimoElemento));
            ListaRectangulosSingleton.getUndo().remove(ListaRectangulosSingleton.getUndo().get(ultimoElemento));
            ListaRectangulosSingleton.listaDeRectangulos = listaAuxiliar; 
            }
        }
    
    
   //   Aqui para Redo 
    public static ArrayList<ArrayList<Rectangulo>> redo = null;
    
    public static ArrayList<ArrayList<Rectangulo>> getRedo(){
        if(ListaRectangulosSingleton.redo == null){
            ListaRectangulosSingleton.redo = new ArrayList<ArrayList<Rectangulo>>();
        }
        return ListaRectangulosSingleton.redo;
    }
    
    public static void rehacer(){
        ArrayList<Rectangulo> listaAuxiliar =  new ArrayList<Rectangulo>();
        int ultimoElemento = ListaRectangulosSingleton.getUndo() == null? -1 : 
                ListaRectangulosSingleton.getUndo().size()-1;
        
        if (ListaRectangulosSingleton.getRedo() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("I have a great message for you!");
            alert.showAndWait();
        }
        else{
            for (int i = 0; i < (ListaRectangulosSingleton.getUndo().get(ultimoElemento).size()-1); i++) {
                listaAuxiliar.add(ListaRectangulosSingleton.getUndo().get(ultimoElemento).get(i));
            }
            ListaRectangulosSingleton.getUndo().add(ListaRectangulosSingleton.getRedo().get(ultimoElemento));
            ListaRectangulosSingleton.getRedo().remove(ListaRectangulosSingleton.getRedo().get(ultimoElemento));
            ListaRectangulosSingleton.listaDeRectangulos = listaAuxiliar;
            }
        }
}
