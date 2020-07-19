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
    private double puntoX;
    private double puntoY;
    private String ID;

    public AdapterRectangulo(double puntoX, double puntoY, String ID) {
        this.puntoX = puntoX;
        this.puntoY = puntoY;
        this.ID = ID;
    }
    
    public AdapterRectangulo() {}

    public double getPuntoX() {
        return puntoX;
    }

    public void setPuntoX(double puntoX) {
        this.puntoX = puntoX;
    }

    public double getPuntoY() {
        return puntoY;
    }

    public void setPuntoY(double puntoY) {
        this.puntoY = puntoY;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    
}
