package com.fabrica.stock.ubicacion.domain;


import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

@Entity
@Table(name = "ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = TextoConstraints.CORTO)
    private String nombre;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(nullable = false)
    private boolean sistema = false;

    protected Ubicacion() {}

    public Ubicacion(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() { return nombre; }

    public void modificarNombre(String nombre) { this.nombre = nombre; }

    public boolean estaActivo() {
        return activo;
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() { this.activo = true; }

    public static Ubicacion sistema(String nombre) {
        Ubicacion ubicacion = new Ubicacion(nombre);
        ubicacion.activo = false;
        ubicacion.sistema = true;
        return ubicacion;
    }
}