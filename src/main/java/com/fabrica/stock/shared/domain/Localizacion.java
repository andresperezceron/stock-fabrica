package com.fabrica.stock.shared.domain;

import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Localizacion {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private NombreUbicacion nombre;

    protected Localizacion() {}

    protected Localizacion(NombreUbicacion nombre) {
        this.nombre = nombre;
    }

    protected Long getId() { return id; }

    protected NombreUbicacion getNombre() {
        return nombre;
    }

    protected void setNombre(NombreUbicacion nombre) { this.nombre = nombre; }
}