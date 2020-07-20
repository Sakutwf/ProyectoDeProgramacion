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
    private String rect1PuntoX;
    private String  rect1puntoY;
    private String  rect2PuntoX;
    private String  rect2puntoY;
    private String ID;

    public AdapterRectangulo(String rect1PuntoX, String rect1puntoY, String rect2PuntoX, String rect2puntoY, String ID) {
        this.rect1PuntoX = rect1PuntoX;
        this.rect1puntoY = rect1puntoY;
        this.rect2PuntoX = rect2PuntoX;
        this.rect2puntoY = rect2puntoY;
        this.ID = ID;
    }

    public String getRect1PuntoX() {
        return rect1PuntoX;
    }

    public void setRect1PuntoX(String rect1PuntoX) {
        this.rect1PuntoX = rect1PuntoX;
    }

    public String getRect1puntoY() {
        return rect1puntoY;
    }

    public void setRect1puntoY(String rect1puntoY) {
        this.rect1puntoY = rect1puntoY;
    }

    public String getRect2PuntoX() {
        return rect2PuntoX;
    }

    public void setRect2PuntoX(String rect2PuntoX) {
        this.rect2PuntoX = rect2PuntoX;
    }

    public String getRect2puntoY() {
        return rect2puntoY;
    }

    public void setRect2puntoY(String rect2puntoY) {
        this.rect2puntoY = rect2puntoY;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


}
