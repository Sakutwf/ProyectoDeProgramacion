/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.PDFToImage;

/**
 *
 * @author Serllet
 */
public class ProyectoProgramacion extends Application {
     private PDDocument document = new PDDocument();
     private ImageView image= new ImageView();
     private Group root2 = new Group();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCargarPDF.fxml"));
        
        stage.setTitle("JavaFX App");

        FileChooser fileChooser = new FileChooser();

        Button button = new Button("Select File");
        button.setOnAction(e -> {
            document.addPage(new PDPage());
            File selectedfile = fileChooser.showOpenDialog(null);
            if(selectedfile !=null){
                try {
                System.out.println(selectedfile.getAbsoluteFile());
                document = PDDocument.load(selectedfile);
                PDFRenderer pr = new PDFRenderer(document);
                BufferedImage img = pr.renderImage(0);
                File outputfile= new File("saved.png");
                ImageIO.write(img, "png", outputfile);
                document.close();
                Image imge = SwingFXUtils.toFXImage(img, null);
                image.setImage(imge);
                image.setPreserveRatio(true);
                this.root2= new Group(image);
                Scene scene2 = new Scene(root2,960,600);
                stage.setScene(scene2);
                stage.show();
                    System.out.println("hola");
                } catch (IOException ex) {
                Logger.getLogger(ProyectoProgramacion.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });       
        VBox vBox = new VBox(button);
        Scene scene = new Scene(vBox, 960, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
