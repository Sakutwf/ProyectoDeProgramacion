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
    private double ancho;
    private double alto;
    private String ID;

    public AdapterRectangulo(double puntoX, double puntoY, double ancho, double alto, String ID) {
        this.puntoX = puntoX;
        this.puntoY = puntoY;
        this.ancho = ancho;
        this.alto = alto;
        this.ID = ID;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public AdapterRectangulo() {
    }

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
