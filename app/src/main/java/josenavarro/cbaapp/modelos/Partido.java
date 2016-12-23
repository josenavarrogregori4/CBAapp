package josenavarro.cbaapp.modelos;

import java.io.Serializable;

/**
 * Created by José on 21/12/2016.
 */

public class Partido implements Serializable{
    private String id;
    private int disputado;
    private String fecha;
    private String hora;
    private int jugadoEnCasa;
    private String nombreRival;
    private int puntosCBA;
    private int puntosRival;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDisputado() {
        return disputado;
    }

    public void setDisputado(int disputado) {
        this.disputado = disputado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getJugadoEnCasa() {
        return jugadoEnCasa;
    }

    public void setJugadoEnCasa(int jugadoEnCasa) {
        this.jugadoEnCasa = jugadoEnCasa;
    }

    public String getNombreRival() {
        return nombreRival;
    }

    public void setNombreRival(String nombreRival) {
        this.nombreRival = nombreRival;
    }

    public int getPuntosCBA() {
        return puntosCBA;
    }

    public void setPuntosCBA(int puntosCBA) {
        this.puntosCBA = puntosCBA;
    }

    public int getPuntosRival() {
        return puntosRival;
    }

    public void setPuntosRival(int puntosRival) {
        this.puntosRival = puntosRival;
    }
}
