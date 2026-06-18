package com.fabrica.stock.producto.domain;

import jakarta.persistence.*;

@Entity
@Table(
        name = "producto",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "codigo")
        }
)

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String codigo;

    @Column(nullable = false, length = 200)
    private String descripcion;

    @Column(nullable = false, length = 30)
    private String unidad;

    @Column(nullable = false, length = 100)
    private String familia;

    @Column(nullable = false)
    private boolean activo = true;

    protected Producto() {
        // JPA
    }

    public Producto(
            String codigo,
            String descripcion,
            String unidad,
            String familia
    ) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.familia = familia;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public String getFamilia() {
        return familia;
    }

    public boolean isActivo() {
        return activo;
    }

    public void activar() {
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }
}