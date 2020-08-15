/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otakus;

import javafx.beans.property.SimpleStringProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author Serllet
 */
public class AreaInteres {
    private SimpleStringProperty id;
    private SimpleStringProperty textoExtraido;

     @JsonCreator
    public AreaInteres(@JsonProperty ("id")String id, @JsonProperty ("textoExtraido")String textoExtraido) {
        this.id = new SimpleStringProperty(id);
        this.textoExtraido = new SimpleStringProperty(textoExtraido);
    }

    public SimpleStringProperty getId() {
        return id;
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public SimpleStringProperty getTextoExtraido() {
        return textoExtraido;
    }

    public void setTextoExtraido(String textoExtraido) {
        this.textoExtraido = new SimpleStringProperty(textoExtraido);
    }
    
    
    
    
    
 

  

    
    
    
    
    
    
}
