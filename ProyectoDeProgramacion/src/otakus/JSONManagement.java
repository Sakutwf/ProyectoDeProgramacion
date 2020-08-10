/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import org.codehaus.jackson.map.ObjectMapper;
/**
 *
 * @author Saaku
 */
public class JSONManagement {
    ObjectMapper objectMapper = new ObjectMapper();
    /**
     * carga un json con un fileChooser y lo retorna como lista
     * @return 
     * @throws java.io.IOException 
     */
    public Rectangulo cargarJSON() throws IOException{ //CAMBIAR A LISTA EN VEZ DE VOID Y VER COMO DIANTRES RECONOCEEL TIPO :(
        FileChooser escogerJSON = new FileChooser();
        escogerJSON.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo JSON", "*.json"));
        File archivoJSON = escogerJSON.showOpenDialog(null);       
        return this.deserealizarListaRectangulos(archivoJSON);
//        ArrayList<Rectangulo> listaCargadaJSON = new ArrayList<Rectangulo>();
//        Rectangulo rect = new ObjectMapper().readValue(archivoJSON.getAbsolutePath(), Rectangulo.class);

    }
    
    public void serializarListaRectangulos(String nombre, ArrayList<Rectangulo> listaRectangulos) throws IOException{
        System.out.println("Se puede serializar?"+objectMapper.canSerialize(Rectangulo.class));
        File y = new File(nombre+".json");  
        if(!y.exists())
            y.createNewFile();
        objectMapper.writeValue(new File(nombre+".json"), listaRectangulos.get(0));
        
      
                  

       
           

        
       
     
        
        
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

   
    
    public Rectangulo deserealizarListaRectangulos(File archivo) throws FileNotFoundException, IOException{ 
        ArrayList<Rectangulo> aux = new ArrayList<>();                  
        Rectangulo a =  objectMapper.readValue(archivo, Rectangulo.class);    
        System.out.println(a.toString());
        return a;

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
