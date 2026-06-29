package com.fabrica.stock.producto.domain;

import com.fabrica.stock.producto.domain.constraints.ProductoConstraints;
import jakarta.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = ProductoConstraints.CODIGO)
    private String codigo;

    @Column(nullable = false, length = ProductoConstraints.DESCRIPCION)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadMedida unidad;

    @Column(nullable = false, length = ProductoConstraints.FAMILIA)
    private String familia;

    @Column(nullable = false)
    private boolean activo = true;

    protected Producto() {
        // JPA
    }

    public Producto(
            String codigo,
            String descripcion,
            UnidadMedida unidad,
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

    public UnidadMedida getUnidad() {
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