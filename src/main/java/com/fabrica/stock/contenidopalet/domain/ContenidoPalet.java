package com.fabrica.stock.contenidopalet.domain;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.TipoCaja;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCaja tipoCaja;

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToMany
    private List<Lote> lotesMateriaPrima;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    public ContenidoPalet(
            Palet palet,
            Lote lote,
            TipoCaja tipoCaja,
            Integer cantidad,
            List<Lote> lotesMateriaPrima)
    {
        this.palet = palet;
        this.lote = lote;
        this.tipoCaja = tipoCaja;
        this.cantidad = cantidad;
        this.lotesMateriaPrima = lotesMateriaPrima;
        fechaCreacion = LocalDateTime.now();
    }

    protected ContenidoPalet() {}

    public Long getId() {
        return id;
    }

    public Palet getPalet() {
        return palet;
    }

    public TipoCaja getTipoCaja() { return tipoCaja; }

    public Lote getLote() { return lote; }

    public Integer getCantidad() { return cantidad; }

    public List<Lote> getLotesMateriaPrima() { return Collections.unmodifiableList(lotesMateriaPrima); }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public boolean esMismoContenido(ContenidoPalet otro) {
        boolean mismoLoteMismoTipo = lote.getCodigo().equals(otro.lote.getCodigo()) && tipoCaja == otro.tipoCaja;

        boolean mismasMateriasPrimas =
                lotesMateriaPrima.stream()
                        .map(Lote::getId)
                        .sorted()
                        .toList()
                        .equals(otro.lotesMateriaPrima.stream()
                                        .map(Lote::getId)
                                        .sorted()
                                        .toList());

        return mismoLoteMismoTipo && mismasMateriasPrimas;
    }

    public void acumular(ContenidoPalet otro) {
        cantidad += otro.cantidad;
    }
}