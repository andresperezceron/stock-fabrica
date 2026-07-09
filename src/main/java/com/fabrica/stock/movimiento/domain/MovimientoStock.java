package com.fabrica.stock.movimiento.domain;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento_stock")
public class MovimientoStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lote_id", nullable = false)
    private Lote lote;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimiento tipo;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal cantidad;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origen_id")
    private Ubicacion origen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destino_id")
    private Ubicacion destino;

    protected MovimientoStock() {
    }

    public MovimientoStock(Producto producto,
                           Lote lote,
                           TipoMovimiento tipo,
                           BigDecimal cantidad,
                           LocalDateTime fecha,
                           Ubicacion origen,
                           Ubicacion destino)
    {
        this.producto = producto;
        this.lote = lote;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public Producto getProducto() { return producto; }

    public Lote getLote() {
        return lote;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Ubicacion getOrigen() { return origen; }

    public Ubicacion getDestino() { return destino; }
}