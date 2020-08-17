/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Saaku
 */
public class JSONManagement {

    /**
     * carga un json con un fileChooser y lo retorna como lista
     *
     * @return
     * @throws java.io.IOException
     */
    ObjectMapper objectMapper = new ObjectMapper();
    static ArrayList<Rectangulo> aux;
    public static File archivoJSON;

    /**
     * carga un json con un fileChooser y lo retorna como lista
     *
     * @return
     * @throws java.io.IOException
     */
    public ArrayList<Rectangulo> cargarJSON() { //CAMBIAR A LISTA EN VEZ DE VOID Y VER COMO DIANTRES RECONOCEEL TIPO :(
        FileChooser escogerJSON = new FileChooser();
        escogerJSON.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo JSON", "*.json"));
        archivoJSON = escogerJSON.showOpenDialog(null);
        System.out.println("ejecute este cuadro de dialogo");

        if (archivoJSON.exists()) {
            try {
                return this.deserealizarListaRectangulos(archivoJSON);
            } catch (FileNotFoundException fnf) {
                System.out.println("Error FileNotFoundException" + fnf);
            } catch (IOException io) {
                System.out.println("Error IOException" + io);

            }
            Valor.path = archivoJSON.getAbsolutePath();

        }

        System.out.println("Archivo no compatible");
        return new ArrayList<Rectangulo>();

    }

    public void serializarListaRectangulos(String nombre, ArrayList<Rectangulo> listaRectangulos) {
        System.out.println("Se puede serializar?" + objectMapper.canSerialize(Rectangulo.class));
        ArrayList<Rectangulo> listaAux = new ArrayList<>();

        for (Rectangulo r : ListaRectangulosSingleton.getRectangulos()) {
            Rectangulo rect = new Rectangulo();
            rect.setColorR(r.getColorR());
            rect.setColorG(r.getColorG());
            rect.setColorB(r.getColorB());
            rect.setContenido(r.getContenido());
            rect.setId(r.getId());
            rect.setInicio(r.getInicio());
            rect.setFin(r.getFin());
            System.out.println("Guardando id: " + r.getId());
            listaAux.add(rect);
        }
        try {
            File y = new File(nombre);
            if (!y.exists()) {
                y.createNewFile();
            }

            //objectMapper.writeValue(y, listaAux);
            String objectAsString = objectMapper.writeValueAsString(listaAux);
            FileWriter writer = new FileWriter(y.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(objectAsString);
            bw.flush();
            bw.close();
            Valor.path = y.getAbsolutePath();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Rectangulo> deserealizarListaRectangulos(File archivo) throws FileNotFoundException, JsonGenerationException, JsonMappingException, IOException {
        aux = new ArrayList<>();

        String strCurrentLine = "";
        String contenido = "";
        BufferedReader objReader = new BufferedReader(new FileReader(archivo.getAbsoluteFile()));

        while ((strCurrentLine = objReader.readLine()) != null) {
            contenido = contenido + strCurrentLine;
        }

        if (!contenido.equals("")) {
            JsonNode jsonNode = objectMapper.readTree(contenido); //Convietrte de json a arbol[Nodos]
            Iterator<JsonNode> jsonIt = jsonNode.getElements();

            while (jsonIt.hasNext()) {
                JsonNode elemento = jsonIt.next();

                String id = elemento.get("id").toString();
                id = id.substring(1, id.length() - 1);
                int colorR = elemento.get("colorR").getIntValue();
                int colorG = elemento.get("colorG").getIntValue();
                int colorB = elemento.get("colorB").getIntValue();

                Punto Inicio = new Punto((elemento.get("inicio")).get("x").getIntValue(), (elemento.get("inicio")).get("y").getIntValue());
                Punto Fin = new Punto((elemento.get("fin")).get("x").getIntValue(), (elemento.get("fin")).get("y").getIntValue());
                String contenidoR = elemento.get("contenido").toString();

                Rectangulo r = new Rectangulo(id, colorR, colorG, colorB, Inicio, Fin, contenidoR);

                aux.add(r);
            }

        }

        objReader.close();
        return aux;
    }

    public void serializarPlantillaCargada() {
        serializarListaRectangulos(this.archivoJSON.getName(), ListaRectangulosSingleton.getRectangulos());
    }

    public void eliminarPlantillaCargada() {
        try {
            File archivo = this.archivoJSON;
            boolean estatus = archivo.delete();;
            if (!estatus) {
                System.out.println("Error no se ha podido eliminar el  archivo");
            } else {
                System.out.println("Se ha eliminado el archivo exitosamente");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void serializarPlantillaCargada2() {
        if (Valor.path.equals("")) {
            return;
        }
        serializarListaRectangulos(Valor.path, ListaRectangulosSingleton.getRectangulos());
    }

    public static void generarArchivoJsonInformacionExtraida(ObservableList<AreaInteres> informacion, String nombre) {
        String nombreArchivo = nombre + ".xlsx";

        String hoja = "Grilla";

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);

        // Cabecera de la hoja de excel
        String[] header = new String[]{"ID", "INFORMACION EXTRAIDA"};

        // Contenido de la hoja de excel
        // Poner en negrita la cabecera
        CellStyle style = libro.createCellStyle();
        XSSFFont font = libro.createFont();
        font.setBold(true);
        style.setFont(font);

        // Generar los datos para el documento
        int k = 0;
        for (int i = 0; i <= informacion.size() - 1; i++) {
            XSSFRow row = hoja1.createRow(i); // Se crea la fila
            for (int j = 0; j < header.length; j++) {
                if (k == 0) { // Para la cabecera
                    XSSFCell cell = row.createCell(j); // Se crean las celdas pra la cabecera
                    cell.setCellValue(header[j]); // Se añade el contenido
                } else {
                    if (j == 0) {
                        XSSFCell cell = row.createCell(j); // Se crean las celdas para el contenido
                        cell.setCellValue(informacion.get(i - 1).getId().getValue());
                    } else {
                        XSSFCell cell = row.createCell(j); // Se crean las celdas para el contenido
                        cell.setCellValue(informacion.get(i - 1).getTextoExtraido().getValue());
                    }
                }

                // Se añade el contenido
            }
            k++;
        }
        XSSFRow row = hoja1.createRow(k);
        for (int l = 0; l < header.length; l++) {
            if (l == 0) {
                XSSFCell cell = row.createCell(l); // Se crean las celdas para el contenido
                cell.setCellValue(informacion.get(k - 1).getId().getValue());
            } else {
                XSSFCell cell = row.createCell(l); // Se crean las celdas para el contenido
                cell.setCellValue(informacion.get(k - 1).getTextoExtraido().getValue());
            }
        }

        // Crear el archivo
        try (OutputStream fileOut = new FileOutputStream(nombreArchivo)) {
            System.out.println("SE CREO EL EXCEL");
            libro.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
