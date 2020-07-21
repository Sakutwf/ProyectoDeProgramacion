/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion;

import com.sun.javafx.sg.prism.NGShape;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Saaku
 */
public class Rectangulo extends Rectangle {

    SimpleDoubleProperty rectinitX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectinitY = new SimpleDoubleProperty();
    SimpleDoubleProperty rectX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectY = new SimpleDoubleProperty();

    GraphicsContext gc;
    private Color colorRectangulo;
    private String dato;
    private double ancho;
    private double alto;

    public Rectangulo() {
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

    public SimpleDoubleProperty getRectinitX() {
        return rectinitX;
    }

    public void setRectinitX(SimpleDoubleProperty rectinitX) {
        this.rectinitX = rectinitX;
    }

    public SimpleDoubleProperty getRectinitY() {
        return rectinitY;
    }

    public void setRectinitY(SimpleDoubleProperty rectinitY) {
        this.rectinitY = rectinitY;
    }

    public SimpleDoubleProperty getRectX() {
        return rectX;
    }

    public void setRectX(SimpleDoubleProperty rectX) {
        this.rectX = rectX;
    }

    public SimpleDoubleProperty getRectY() {
        return rectY;
    }

    public void setRectY(SimpleDoubleProperty rectY) {
        this.rectY = rectY;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public Color getColorRectangulo() {
        return colorRectangulo;
    }

    public void setColorRectangulo(Color colorRectangulo) {
        this.colorRectangulo = colorRectangulo;
    }

    public NGShape.Mode getImpl_mode() {
        return impl_mode;
    }

    public void setImpl_mode(NGShape.Mode impl_mode) {
        this.impl_mode = impl_mode;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

}
