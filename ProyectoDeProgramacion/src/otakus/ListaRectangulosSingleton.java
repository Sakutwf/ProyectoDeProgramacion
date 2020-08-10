/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Serllet & Escarlet
 */
public class ListaRectangulosSingleton {

    public static ArrayList<Rectangulo> listaDeRectangulos = null;

    public static ArrayList<Rectangulo> getRectangulos() {
        if (ListaRectangulosSingleton.listaDeRectangulos == null) {
            ListaRectangulosSingleton.listaDeRectangulos = new ArrayList<Rectangulo>();
            //System.out.println("revisando si es nula");
        }
        return ListaRectangulosSingleton.listaDeRectangulos;
    }

    public static void setRectangulos(ArrayList<Rectangulo> listaParaSet) {
        ListaRectangulosSingleton.listaDeRectangulos = listaParaSet;

    }

}
