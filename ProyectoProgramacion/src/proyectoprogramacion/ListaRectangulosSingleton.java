/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Saaku
 */
public class ListaRectangulosSingleton {
    public static ArrayList<Rectangle> listaDeRectangulos = null;
    
    public static ArrayList<Rectangle> getRectangulos(){
        if(ListaRectangulosSingleton.listaDeRectangulos == null){
            ListaRectangulosSingleton.listaDeRectangulos = new ArrayList<Rectangle>();
        }
        return ListaRectangulosSingleton.listaDeRectangulos;
    }
    
    public static ArrayList<Rectangle> Undo = null;
    
    public static ArrayList<Rectangle> getUndo(){
        if(ListaRectangulosSingleton.Undo == null){
            ListaRectangulosSingleton.Undo = new ArrayList<Rectangle>();
        }
        return ListaRectangulosSingleton.Undo;
    }
    
    
    public static ArrayList<Rectangle> Redo = null;
    
    public static ArrayList<Rectangle> getRedo(){
        if(ListaRectangulosSingleton.Redo == null){
            ListaRectangulosSingleton.Redo = new ArrayList<Rectangle>();
        }
        return ListaRectangulosSingleton.Redo;
    }
    
    
    
    
}
