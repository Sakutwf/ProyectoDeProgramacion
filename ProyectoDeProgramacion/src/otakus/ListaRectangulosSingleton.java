/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Serllet & Escarlet
 */
public class ListaRectangulosSingleton {
    public static ArrayList<Rectangulo> listaDeRectangulos = null;
    
    public static ArrayList<Rectangulo> getRectangulos(){
        if(ListaRectangulosSingleton.listaDeRectangulos == null){
            ListaRectangulosSingleton.listaDeRectangulos = new ArrayList<Rectangulo>();
        }
        return ListaRectangulosSingleton.listaDeRectangulos;
    }

    public static void serializarListaRectangulos(String nombre) throws IOException {
            java.lang.reflect.Type tipo = new TypeToken<ArrayList<Rectangulo>>() {
            }.getType();
            String json = new GsonBuilder().setPrettyPrinting().create().toJson(ListaRectangulosSingleton.getRectangulos(), tipo);
            FileWriter writer = new FileWriter(nombre);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(json);
            bw.flush();
            bw.close();

//          String json = new Gson().toJson(ListaRectangulosSingleton.listaDeRectangulos, tipo);
//          Gson gs = new GsonBuilder().registerTypeAdapter(tipo,ListaRectangulosSingleton.listaDeRectangulos).setPrettyPrinting().create();
//          String json = gs.toJson(ListaRectangulosSingleton.listaDeRectangulos, tipo);
            System.out.println(json);
    }

    /**
     * Lectura de archivo en formato Json de los rectangulos con la información
     * seleccionada de la orden de compra.
     *
     * @return Listado de rectangulos con las coordenadas y dimensiones.
     */
    public static List<Rectangulo> deseralizarListaRectangulos() {

        String fichero = "";

        try (BufferedReader br = new BufferedReader(new FileReader("ListaRectangulosJson"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            java.lang.reflect.Type listType = new TypeToken<List<Rectangulo>>() {
            }.getType();
            ArrayList<Rectangulo> nuevaLista = gson.fromJson(fichero, listType);

            return nuevaLista;

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }


}
