package com.fabrica.stock.estacion.domain;

import jakarta.persistence.*;

@Entity
public class Estacion extends com.fabrica.stock.shared.domain.Localizacion {

    protected Estacion() {}

    public Estacion(String nombre) {
        super(nombre);
    }

    public String getNombre() {
        return super.getNombre();
    }
}