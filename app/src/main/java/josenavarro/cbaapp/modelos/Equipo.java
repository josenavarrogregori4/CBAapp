package josenavarro.cbaapp.modelos;

import java.io.Serializable;

/**
 * Created by José on 20/12/2016.
 */
public class Equipo implements Serializable{
    private String id;
    private String nombre;
    private String imagen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
