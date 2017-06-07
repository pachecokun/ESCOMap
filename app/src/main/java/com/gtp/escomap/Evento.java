package com.gtp.escomap;

/**
 * Created by david on 6/06/17.
 */

public class Evento {
    double lat;
    double lon;
    String nombre;
    String descripcion;

    public Evento(double lat, double lon, String nombre, String descripcion) {
        this.lat = lat;
        this.lon = lon;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
