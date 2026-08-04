package com.fabrica.stock.estacion.domain;

import com.fabrica.stock.shared.domain.Localizacion;
import jakarta.persistence.*;

@Entity
public class Estacion extends Localizacion {

    protected Estacion() {}

    public Estacion(String nombre) {
        super(nombre);
    }

    public String getNombre() {
        return super.getNombre();
    }
}