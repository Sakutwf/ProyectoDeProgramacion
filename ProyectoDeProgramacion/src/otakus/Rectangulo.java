
package otakus;

import javafx.scene.paint.Color;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author Serllet & Escarlet
 */
public class Rectangulo {
    private String id;
    private int colorR;
    private int colorG;
    private int colorB;
    private Punto Inicio;
    private Punto Fin;
    private String contenido;
    
    @JsonCreator
    public Rectangulo(@JsonProperty ("Inicio") Punto Inicio, @JsonProperty("Fin") Punto Fin) {
        this.Inicio = Inicio;
        this.Fin = Fin;
    }
    
    @JsonCreator
    public Rectangulo(@JsonProperty ("tipo") String tipo, @JsonProperty ("colorR") int colorR,
            @JsonProperty ("colorG") int colorG, @JsonProperty ("colorB") int colorB,
            @JsonProperty ("Inicio") Punto Inicio, @JsonProperty ("Fin") Punto Fin){
        
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
        this.Inicio = Inicio;
        this.Fin = Fin;
        this.contenido = contenido;
    }

    public Rectangulo() {}
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Punto getInicio() {
        return Inicio;
    }

    public void setInicio(Punto Inicio) {
        this.Inicio = Inicio;
    }

    public Punto getFin() {
        return Fin;
    }

    public void setFin(Punto Fin) {
        this.Fin = Fin;
    }    
    
    public int getAncho() {
        return Fin.getX() - Inicio.getX();
    }
    
    public int getAlto() {
        return Fin.getY() - Inicio.getY();
    }

    public int getColorR() {
        return colorR;
    }

    public void setColorR(int colorR) {
        this.colorR = colorR;
    }

    public int getColorG() {
        return colorG;
    }

    public void setColorG(int colorG) {
        this.colorG = colorG;
    }

    public int getColorB() {
        return colorB;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public Color getColor(){
        Color color = Color.rgb(this.colorR, this.colorG, this.colorB);
        return color;
    }
    
}