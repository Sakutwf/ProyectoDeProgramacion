/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Saaku
 */
public class JSONManagement {

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * carga un json con un fileChooser y lo retorna como lista
     *
     * @return
     * @throws java.io.IOException
     */
    public Rectangulo cargarJSON() throws IOException { //CAMBIAR A LISTA EN VEZ DE VOID Y VER COMO DIANTRES RECONOCEEL TIPO :(
        FileChooser escogerJSON = new FileChooser();
        escogerJSON.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo JSON", "*.json"));
        File archivoJSON = escogerJSON.showOpenDialog(null);
        return this.deserealizarListaRectangulos(archivoJSON);

    }

    public void serializarListaRectangulos(String nombre, ArrayList<Rectangulo> listaRectangulos) throws IOException {
        System.out.println("Se puede serializar?" + objectMapper.canSerialize(Rectangulo.class));
        File y = new File(nombre + ".json");
        if (!y.exists()) {
            y.createNewFile();
        }
        objectMapper.writeValue(new File(nombre + ".json"), listaRectangulos.get(0));
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

    public Rectangulo deserealizarListaRectangulos(File archivo) throws FileNotFoundException, IOException {
        ArrayList<Rectangulo> aux = new ArrayList<>();
        Rectangulo a = objectMapper.readValue(archivo, Rectangulo.class);
        System.out.println(a.toString());
        return a;
    }
}
