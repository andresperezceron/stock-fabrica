package com.fabrica.stock.estacion.domain;

import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

@Entity
@Table(name = "estacion")
public class Estacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = TextoConstraints.CORTO)
    private String nombre;

    protected Estacion() {}

    public Estacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
}