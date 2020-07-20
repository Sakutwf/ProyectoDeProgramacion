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
    private double Punto1X;
    private double punto1Y;
    private double Punto2X;
    private double punto2Y;
    private String ID;

    public AdapterRectangulo(double Punto1X, double punto1Y, double Punto2X, double punto2Y, String ID) {
        this.Punto1X = Punto1X;
        this.punto1Y = punto1Y;
        this.Punto2X = Punto2X;
        this.punto2Y = punto2Y;
        this.ID = ID;
    }

    public double getPunto1X() {
        return Punto1X;
    }

    public void setPunto1X(double Punto1X) {
        this.Punto1X = Punto1X;
    }

    public double getPunto1Y() {
        return punto1Y;
    }

    public void setPunto1Y(double punto1Y) {
        this.punto1Y = punto1Y;
    }

    public double getPunto2X() {
        return Punto2X;
    }

    public void setPunto2X(double Punto2X) {
        this.Punto2X = Punto2X;
    }

    public double getPunto2Y() {
        return punto2Y;
    }

    public void setPunto2Y(double punto2Y) {
        this.punto2Y = punto2Y;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    
}
