/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.annotation.processing.FilerException;

/**
 *
 * @author Saaku
 */
public class ListaRectangulosSingleton {

    public static ArrayList<Rectangulo> listaDeRectangulos = null;

    public static ArrayList<Rectangulo> getRectangulos() {
        if (ListaRectangulosSingleton.listaDeRectangulos == null) {
            ListaRectangulosSingleton.listaDeRectangulos = new ArrayList<Rectangulo>();
        }
        return ListaRectangulosSingleton.listaDeRectangulos;
    }

    public static void serializarListaRectangulos() {
        try {
            ArrayList<AdapterRectangulo> listaAdaptada = new ArrayList<AdapterRectangulo>();
            for (int i = 0; i < ListaRectangulosSingleton.getRectangulos().size(); i++) {
                double x = ListaRectangulosSingleton.getRectangulos().get(i).getX();
                double y = ListaRectangulosSingleton.getRectangulos().get(i).getY();
                double ancho = ListaRectangulosSingleton.getRectangulos().get(i).getAncho();
                double alto = ListaRectangulosSingleton.getRectangulos().get(i).getAlto();
                System.out.println("ancho3: " + ancho);
                String id = ListaRectangulosSingleton.getRectangulos().get(i).getDato();
                AdapterRectangulo rectanguloAdaptado = new AdapterRectangulo(x, y, ancho, alto, id);
                listaAdaptada.add(rectanguloAdaptado);

            }
            Type tipo = new TypeToken<ArrayList<AdapterRectangulo>>() {
            }.getType();
            String json = new GsonBuilder().setPrettyPrinting().create().toJson(listaAdaptada, tipo);
            FileWriter writer = new FileWriter("ListaRectangulosJson", true);
            BufferedWriter bw = new BufferedWriter(writer);

            bw.write(json);
            bw.flush();
            bw.close();      //como puedo hacer salto de linea entre objetos?

//String json = new Gson().toJson(ListaRectangulosSingleton.listaDeRectangulos, tipo);
//           Gson gs = new GsonBuilder().registerTypeAdapter(tipo,ListaRectangulosSingleton.listaDeRectangulos).setPrettyPrinting().create();
//          String json = gs.toJson(ListaRectangulosSingleton.listaDeRectangulos, tipo);
            System.out.println(json);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

    /**
     * Lectura de archivo en formato Json de los rectangulos con la información
     * seleccionada de la orden de compra.
     *
     * @return Listado de rectangulos con las coordenadas y dimensiones.
     */
    public static List<AdapterRectangulo> deseralizarListaRectangulos() {

        String fichero = "";

        try (BufferedReader br = new BufferedReader(new FileReader("ListaRectangulosJson"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Type listType = new TypeToken<List<AdapterRectangulo>>() {
            }.getType();
            ArrayList<AdapterRectangulo> listaAdaptada = gson.fromJson(fichero, listType);

            return listaAdaptada;

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

//   Aqui para Undo
    public static ArrayList<ArrayList<Rectangulo>> undo = null;

    public static ArrayList<ArrayList<Rectangulo>> getUndo() {
        if (ListaRectangulosSingleton.undo == null) {
            ListaRectangulosSingleton.undo = new ArrayList<ArrayList<Rectangulo>>();
        }
        return ListaRectangulosSingleton.undo;
    }

    public static void deshacer() {
        ArrayList<Rectangulo> listaAuxiliar = new ArrayList<Rectangulo>();
        int ultimoElemento = ListaRectangulosSingleton.getUndo() == null ? 0
                : ListaRectangulosSingleton.getUndo().size() - 1;

        if (ListaRectangulosSingleton.getUndo() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("I have a great message for you!");
            alert.showAndWait();
        } else {
            for (int i = 0; i < (ListaRectangulosSingleton.getUndo().get(ultimoElemento).size() - 1); i++) {
                listaAuxiliar.add(ListaRectangulosSingleton.getUndo().get(ultimoElemento).get(i));
            }
            ListaRectangulosSingleton.getRedo().add(ListaRectangulosSingleton.getUndo().get(ultimoElemento));
            ListaRectangulosSingleton.getUndo().remove(ListaRectangulosSingleton.getUndo().get(ultimoElemento));
            ListaRectangulosSingleton.listaDeRectangulos = listaAuxiliar;
        }
    }

    //   Aqui para Redo 
    public static ArrayList<ArrayList<Rectangulo>> redo = null;

    public static ArrayList<ArrayList<Rectangulo>> getRedo() {
        if (ListaRectangulosSingleton.redo == null) {
            ListaRectangulosSingleton.redo = new ArrayList<ArrayList<Rectangulo>>();
        }
        return ListaRectangulosSingleton.redo;
    }

    public static void rehacer() {
        ArrayList<Rectangulo> listaAuxiliar = new ArrayList<Rectangulo>();
        int ultimoElemento = ListaRectangulosSingleton.getUndo() == null ? -1
                : ListaRectangulosSingleton.getUndo().size() - 1;

        if (ListaRectangulosSingleton.getRedo() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("I have a great message for you!");
            alert.showAndWait();
        } else {
            for (int i = 0; i < (ListaRectangulosSingleton.getUndo().get(ultimoElemento).size() - 1); i++) {
                listaAuxiliar.add(ListaRectangulosSingleton.getUndo().get(ultimoElemento).get(i));
            }
            ListaRectangulosSingleton.getUndo().add(ListaRectangulosSingleton.getRedo().get(ultimoElemento));
            ListaRectangulosSingleton.getRedo().remove(ListaRectangulosSingleton.getRedo().get(ultimoElemento));
            ListaRectangulosSingleton.listaDeRectangulos = listaAuxiliar;
        }
    }
}
