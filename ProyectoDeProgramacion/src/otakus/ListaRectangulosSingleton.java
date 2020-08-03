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
public class ListaRectangulosSingleton {
    public static ArrayList<Rectangulo> listaDeRectangulos = null;
    
    public static ArrayList<Rectangulo> getRectangulos(){
        if(ListaRectangulosSingleton.listaDeRectangulos == null){
            ListaRectangulosSingleton.listaDeRectangulos = new ArrayList<Rectangulo>();
        }
        return ListaRectangulosSingleton.listaDeRectangulos;
    }
}
