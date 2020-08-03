
package otakus;

import javafx.scene.paint.Color;

/**
 *
 * @author Serllet & Escarlet
 */
public class Rectangulo {
    private String tipo;
    private Color color;
    private Punto Inicio;
    private Punto Fin;

    public Rectangulo() {}
    
    public Rectangulo(Punto Inicio, Punto Fin) {
        this.Inicio = Inicio;
        this.Fin = Fin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
    
}
