/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.stage.FileChooser;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author Saaku
 */
public class JSONManagement {

    ObjectMapper objectMapper = new ObjectMapper();
    static ArrayList<Rectangulo> aux;
    /**
     * carga un json con un fileChooser y lo retorna como lista
     *
     * @return
     * @throws java.io.IOException
     */
    public ArrayList<Rectangulo> cargarJSON() { //CAMBIAR A LISTA EN VEZ DE VOID Y VER COMO DIANTRES RECONOCEEL TIPO :(
        FileChooser escogerJSON = new FileChooser();
        escogerJSON.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo JSON", "*.json"));
        File archivoJSON = escogerJSON.showOpenDialog(null);
        System.out.println("ejecute este cuadro de dialogo");

        if (archivoJSON.exists()) {
            try {
                return this.deserealizarListaRectangulos(archivoJSON);
            } catch (FileNotFoundException fnf) {
                System.out.println("Error FileNotFoundException" + fnf);
            } catch (IOException io) {
                System.out.println("Error IOException" + io);

            }

        }
        System.out.println("Archivo no compatible");
        return new ArrayList<Rectangulo>();

//        ArrayList<Rectangulo> listaCargadaJSON = new ArrayList<Rectangulo>();
//        Rectangulo rect = new ObjectMapper().readValue(archivoJSON.getAbsolutePath(), Rectangulo.class);
    }

    public void serializarListaRectangulos(String nombre, ArrayList<Rectangulo> listaRectangulos) {
        System.out.println("Se puede serializar?" + objectMapper.canSerialize(Rectangulo.class));
        ArrayList<Rectangulo> listaAux = new ArrayList<>();
       
       
        for (Rectangulo r : ListaRectangulosSingleton.getRectangulos()) {
            Rectangulo rect = new Rectangulo();
            rect.setColorR(r.getColorR());
            rect.setColorR(r.getColorG());
            rect.setColorR(r.getColorB());
            rect.setContenido(r.getContenido());
            rect.setId(r.getId());
            rect.setInicio(r.getInicio());
            rect.setFin(r.getFin());
            System.out.println("Guardando id: "+r.getId());
            listaAux.add(rect);
        }
        try {
            //String aux = objectMapper.writeValueAsString(listaRectangulos);
            //    System.out.println(aux);
            File y = new File(nombre + ".json");
            if (!y.exists()) {
                y.createNewFile();
            }
            //   BufferedWriter bw = new BufferedWriter(new FileWriter(y));
            //  bw.write(aux);
            //   bw.close();

            //objectMapper.writeValue(y, listaAux);
            String objectAsString = objectMapper.writeValueAsString(listaAux);
            FileWriter writer = new FileWriter(y.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(objectAsString);
            bw.flush();
            bw.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

//            java.lang.reflect.Type tipo = new TypeToken<ArrayList<Rectangulo>>() {
//            }.getType();
//            String json = new GsonBuilder().setPrettyPrinting().create().toJson(ListaRectangulosSingleton.getRectangulos(), tipo);
//            FileWriter writer = new FileWriter(nombre);
//            BufferedWriter bw = new BufferedWriter(writer);
//            bw.write(json);
//            bw.flush();
//            bw.close();
//          String json = new Gson().toJson(ListaRectangulosSingleton.listaDeRectangulos, tipo);
//          Gson gs = new GsonBuilder().registerTypeAdapter(tipo,ListaRectangulosSingleton.listaDeRectangulos).setPrettyPrinting().create();
//          String json = gs.toJson(ListaRectangulosSingleton.listaDeRectangulos, tipo);
//            System.out.println(json);
    }

    public ArrayList<Rectangulo> deserealizarListaRectangulos(File archivo) throws FileNotFoundException, JsonGenerationException, JsonMappingException, IOException {
        aux = new ArrayList<>();
        //BufferedReader br = new BufferedReader(new FileReader(archivo));
        //String x = br.readLine();
        //br.close();
        //aux = objectMapper.readValue(x, new TypeReference<ArrayList<Rectangulo>>() {} );

        String strCurrentLine ="";
        String contenido = "";
        BufferedReader objReader = new BufferedReader(new FileReader(archivo.getAbsoluteFile()));
        
        while ((strCurrentLine = objReader.readLine()) != null)
        {
            contenido=contenido + strCurrentLine;
        }

        if(!contenido.equals("")){
             JsonNode jsonNode = objectMapper.readTree(contenido); //Convietrte de json a arbol[Nodos]
             Iterator<JsonNode> jsonIt = jsonNode.getElements(); 
             
             while (jsonIt.hasNext()) {
                JsonNode elemento = jsonIt.next();
                
                String id = elemento.get("id").toString();
                id = id.substring(1,id.length()-1);
                int colorR = elemento.get("colorR").getIntValue();
                int colorG = elemento.get("colorG").getIntValue();
                int colorB = elemento.get("colorB").getIntValue();
                //String x = (elemento.get("inicio")).toString();
                // System.out.println("Value x "+x);
                Punto Inicio = new Punto((elemento.get("inicio")).get("x").getIntValue(), (elemento.get("inicio")).get("y").getIntValue());
                Punto Fin = new Punto((elemento.get("fin")).get("x").getIntValue(), (elemento.get("fin")).get("y").getIntValue());
                String contenidoR = elemento.get("contenido").toString();
                //System.out.println("Id: "+elemento.get("id").toString());
                Rectangulo r = new Rectangulo(id, colorR, colorG, colorB, Inicio, Fin, contenidoR);
                
                aux.add(r);
            }
             
        }
      

        return aux;

//        String fichero = "";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(nombreJSON))) {
//            String linea;
//            while ((linea = br.readLine()) != null) {
//                fichero += linea;
//            }
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//            java.lang.reflect.Type listType = new TypeToken<List<Rectangulo>>() {
//            }.getType();
//            ArrayList<Rectangulo> nuevaLista = gson.fromJson(fichero, listType);
//
//            return nuevaLista;
//
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
    }

}
