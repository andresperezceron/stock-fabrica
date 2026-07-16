package com.fabrica.stock.contenidopalet.domain;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ContenidoPalet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Palet palet;

    @ManyToOne(optional = false)
    private Producto producto;

    @ManyToOne(optional = false)
    private Lote lote;

    @Column(nullable = false)
    private BigDecimal cantidad;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToMany
    private final List<Lote> lotesMateriaPrima = new ArrayList<>();

    public ContenidoPalet(Palet palet, Producto producto, Lote lote, BigDecimal cantidad, LocalDateTime fecha) {
        this.palet = palet;
        this.producto = producto;
        this.lote = lote;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    protected ContenidoPalet() {}

    public Long getId() {
        return id;
    }

    public Palet getPalet() {
        return palet;
    }

    public Producto getProducto() { return  producto; }

    public Lote getLote() {
        return lote;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() { return fecha; }

    public List<Lote> getLotesMateriaPrima() { return lotesMateriaPrima; }
}