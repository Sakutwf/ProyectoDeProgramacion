/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import java.util.ArrayList;

/**
 *
 * @author Saaku
 */
public class UndoRedo {
    public static ArrayList<ArrayList<Rectangulo>> listaDeshacer = null;
     public static ArrayList<ArrayList<Rectangulo>> listaRehacer = null; 
        ////Undo
    
    
    public static ArrayList<ArrayList<Rectangulo>> getListaDeshacer(){
        if(UndoRedo.listaDeshacer == null){            
            UndoRedo.listaDeshacer = new ArrayList<ArrayList<Rectangulo>>();
        }
        return UndoRedo.listaDeshacer;
    }
    
    public static void guardarHistorial(ArrayList<Rectangulo> listaActual){
        if(UndoRedo.listaDeshacer.size()<6){
            UndoRedo.listaDeshacer.add(listaActual);
        }
        else{
            UndoRedo.listaDeshacer.remove(UndoRedo.listaDeshacer.get(0));
            UndoRedo.listaDeshacer.add(listaActual);
        }
    }
    
    public static ArrayList<Rectangulo> accionDeshacer(){
        ArrayList<Rectangulo> ultimaListaAgregada = UndoRedo.getListaDeshacer().get(UndoRedo.getListaDeshacer().size()-1);
        UndoRedo.listaDeshacer.remove(UndoRedo.getListaDeshacer().size()-1);
        UndoRedo.manejarAccionRehacer(ultimaListaAgregada);
        return ultimaListaAgregada;
    }
    
    ///Redo
    
    public static ArrayList<ArrayList<Rectangulo>> getListaRehacer(){
        if(UndoRedo.listaRehacer == null){            
            UndoRedo.listaRehacer = new ArrayList<ArrayList<Rectangulo>>();
        }
        return UndoRedo.listaRehacer;
    }
    
    public static void manejarAccionRehacer(ArrayList<Rectangulo> listaParaRehacer){
        if(UndoRedo.listaRehacer.size()<6){
            UndoRedo.listaRehacer.add(listaParaRehacer);
        }
        else{
            UndoRedo.listaRehacer.remove(UndoRedo.listaRehacer.get(0));
            UndoRedo.listaDeshacer.add(listaParaRehacer);
        }
    }
    
    
    public static ArrayList<Rectangulo> accionRehacer(){
        ArrayList<Rectangulo> ultimaListaDeshecha = UndoRedo.getListaRehacer().get(UndoRedo.getListaRehacer().size()-1);
        UndoRedo.listaRehacer.remove(UndoRedo.getListaRehacer().size()-1);
        UndoRedo.guardarHistorial(ultimaListaDeshecha);
        return ultimaListaDeshecha;
    }
    
}
