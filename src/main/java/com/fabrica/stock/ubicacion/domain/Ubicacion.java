package com.fabrica.stock.ubicacion.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "ubicacion")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = UbicacionConstraints.NOMBRE)
    private String nombre;

    protected Ubicacion() {}

    public Ubicacion(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}