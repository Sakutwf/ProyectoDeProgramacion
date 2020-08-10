/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

/**
 *
 * @author Serllet
 */
public class AreaInteres {

    private String id;
    private String detalle;
    private Punto coordenadasId;
    private Punto coordenadasDetalle;

    public AreaInteres(String id, String detalle, Punto coordenadasId, Punto coordenadasDetalle) {

        this.id = id; //  nombre que se le asigna al rectangulo
        this.detalle = detalle; // resultado del area de interes
        this.coordenadasId = coordenadasId; // coordenadas del canvas donde se dibujara
        this.coordenadasDetalle = coordenadasDetalle; // coordenadas del resultado OCR

    }

    public Punto getCoordenadasId() {
        return coordenadasId;
    }

    public void setCoordenadasId(Punto coordenadasId) {
        this.coordenadasId = coordenadasId;
    }

    public Punto getCoordenadasDetalle() {
        return coordenadasDetalle;
    }

    public void setCoordenadasDetalle(Punto coordenadasDetalle) {
        this.coordenadasDetalle = coordenadasDetalle;
    }

    public Punto getCoordenadas() {
        return coordenadasId;
    }

    public void setCoordenadas(Punto coordenadas) {
        this.coordenadasId = coordenadas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

}
