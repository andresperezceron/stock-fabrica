package com.fabrica.stock.familia.domain;

import com.fabrica.stock.familia.domain.constraints.FamiliaConstraints;
import jakarta.persistence.*;

@Entity
@Table(name = "familia")
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = FamiliaConstraints.NOMBRE)
    private String nombre;

    @Column(nullable = false)
    private boolean activo = true;

    protected Familia() {}

    public Familia(String nombre) { this.nombre = nombre; }

    public Long getId() { return id; }

    public String getNombre() { return nombre; }

    public void modificarNombre(String nombre) { this.nombre = nombre; }

    public boolean estaActivo() {
        return activo;
    }

    public void desactivar() {
        this.activo = false;
    }
}