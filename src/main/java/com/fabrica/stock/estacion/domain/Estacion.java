package com.fabrica.stock.estacion.domain;

import com.fabrica.stock.shared.domain.Localizacion;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import jakarta.persistence.*;

@Entity
public class Estacion extends Localizacion {

    protected Estacion() {}

    public Estacion(NombreUbicacion nombre) {
        super(nombre);
    }

    public NombreUbicacion getNombre() {
        return super.getNombre();
    }
}