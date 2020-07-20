/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

/**
 *
 * @author Saaku
 */
public class AdapterRectangulo {
    private String Punto1X;
    private String punto1Y;
    private String Punto2X;
    private String punto2Y;
    private String ID;

    public AdapterRectangulo(String Punto1X, String punto1Y, String Punto2X, String punto2Y, String ID) {
        this.Punto1X = Punto1X;
        this.punto1Y = punto1Y;
        this.Punto2X = Punto2X;
        this.punto2Y = punto2Y;
        this.ID = ID;
    }

    public String getPunto1X() {
        return Punto1X;
    }

    public void setPunto1X(String Punto1X) {
        this.Punto1X = Punto1X;
    }

    public String getPunto1Y() {
        return punto1Y;
    }

    public void setPunto1Y(String punto1Y) {
        this.punto1Y = punto1Y;
    }

    public String getPunto2X() {
        return Punto2X;
    }

    public void setPunto2X(String Punto2X) {
        this.Punto2X = Punto2X;
    }

    public String getPunto2Y() {
        return punto2Y;
    }

    public void setPunto2Y(String punto2Y) {
        this.punto2Y = punto2Y;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    
}
