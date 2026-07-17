package com.fabrica.stock.familia.domain;

import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

@Entity
@Table(name = "familia")
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = TextoConstraints.CORTO)
    private String nombre;

    protected Familia() {}

    public Familia(String nombre) { this.nombre = nombre; }

    public Long getId() { return id; }

    public String getNombre() { return nombre; }

    public void modificarNombre(String nombre) { this.nombre = nombre; }
}