package com.fabrica.stock.shared.domain;

import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Localizacion {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = TextoConstraints.CORTO)
    private String nombre;

    protected Localizacion() {}

    protected Localizacion(String nombre) {
        this.nombre = nombre;
    }

    protected Long getId() { return id; }

    protected String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) { this.nombre = nombre; }
}