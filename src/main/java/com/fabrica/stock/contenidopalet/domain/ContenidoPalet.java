package com.fabrica.stock.contenidopalet.domain;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.palet.domain.Palet;
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
    private Lote lote;

    @Column(nullable = false)
    private BigDecimal cantidad;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToMany
    private final List<Lote> lotesMateriaPrima = new ArrayList<>();

    public ContenidoPalet(Palet palet, Lote lote, BigDecimal cantidad) {
        this.palet = palet;
        this.lote = lote;
        this.cantidad = cantidad;
        fechaCreacion = LocalDateTime.now();
    }

    protected ContenidoPalet() {}

    public Long getId() {
        return id;
    }

    public Palet getPalet() {
        return palet;
    }

    public Lote getLote() { return lote; }

    public BigDecimal getCantidad() { return cantidad; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public List<Lote> getLotesMateriaPrima() { return lotesMateriaPrima; }
}