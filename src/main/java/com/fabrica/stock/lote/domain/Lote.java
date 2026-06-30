package com.fabrica.stock.lote.domain;

import com.fabrica.stock.lote.domain.constraints.LoteConstrints;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lote")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = LoteConstrints.CODIGO_LOTE)
    private String codigoLote;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigenLote origen;

    @Column(length = LoteConstrints.CODIGO_LOTE_PROVEEDOR)
    private String loteProveedor;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "producto_id",
            nullable = false
    )
    private Producto producto;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal cantidadInicial;

    protected Lote() {
        // JPA
    }

    public Lote(
            String codigoLote,
            OrigenLote origen,
            String loteProveedor,
            Producto producto,
            LocalDateTime fechaCreacion,
            BigDecimal cantidadInicial
    ) {
        this.codigoLote = codigoLote;
        this.origen = origen;
        this.loteProveedor = loteProveedor;
        this.producto = producto;
        this.fechaCreacion = fechaCreacion;
        this.cantidadInicial = cantidadInicial;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public OrigenLote getOrigen() {
        return origen;
    }

    public String getLoteProveedor() {
        return loteProveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public BigDecimal getCantidadInicial() {
        return cantidadInicial;
    }
}