package com.fabrica.stock.producto.domain;

import com.fabrica.stock.familia.domain.Familia;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "familia_id")
    private Familia familia;

    @Column(nullable = false, unique = true)
    private int piezasPorCaja;

    @Column(nullable = false)
    private boolean activo = true;

    protected Producto() {}

    public Producto(
            String codigo,
            String descripcion,
            UnidadMedida unidad,
            Familia familia,
            int piezasPorCaja)
    {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.familia = familia;
        this.piezasPorCaja = piezasPorCaja;
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

    public Familia getFamilia() { return familia; }

    public int getPiezasPorCaja() { return piezasPorCaja; }

    public boolean estaActivo() { return activo; }

    public void desactivar() { this.activo = false; }

    public void actualizar(String codigo,
                           String descripcion,
                           UnidadMedida unidad,
                           Familia familia,
                           int piezasPorCaja)
    {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.familia = familia;
        this.piezasPorCaja = piezasPorCaja;
    }
}