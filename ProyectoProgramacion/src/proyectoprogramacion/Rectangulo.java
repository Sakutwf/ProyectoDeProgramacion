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
    private SimpleDoubleProperty rectinitX = new SimpleDoubleProperty();
    private SimpleDoubleProperty rectinitY = new SimpleDoubleProperty();
    private SimpleDoubleProperty rectX = new SimpleDoubleProperty();
    private SimpleDoubleProperty rectY = new SimpleDoubleProperty();
    GraphicsContext gc;
    private Color colorRectangulo;
    private String dato;

    public Rectangulo() {}

    ///para rectangulo transformado 
    public double getRectinitXdouble() {
        return rectinitX.getValue();
    }
    public double getRectinitYdouble() {
        return rectinitY.getValue();
    }
    public double getRectXdouble() {
        return rectX.getValue();
    }
    public double getRectYdouble() {
        return rectY.getValue();
    }

    public SimpleDoubleProperty getRectinitX() {
        return rectinitY;
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
