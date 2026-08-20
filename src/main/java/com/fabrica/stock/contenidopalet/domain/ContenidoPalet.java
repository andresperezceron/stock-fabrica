package com.fabrica.stock.contenidopalet.domain;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.palet.domain.Palet;
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
    private TipoContenido tipoContenido;

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToMany
    private List<Lote> lotesMateriaPrima;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    public ContenidoPalet(
            Palet palet,
            Lote lote,
            TipoContenido tipoContenido,
            Integer cantidad,
            List<Lote> lotesMateriaPrima)
    {
        this.palet = palet;
        this.lote = lote;
        this.tipoContenido = tipoContenido;
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

    public TipoContenido getTipo() { return tipoContenido; }

    public Lote getLote() { return lote; }

    public Integer getCantidad() { return cantidad; }

    public List<Lote> getLotesMateriaPrima() { return Collections.unmodifiableList(lotesMateriaPrima); }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public boolean esMismoContenido(ContenidoPalet otro) {
        boolean mismoLoteMismoTipo = lote.getCodigo().equals(otro.lote.getCodigo()) && tipoContenido == otro.tipoContenido;
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

    public boolean esMismoLoteTipoCaja(ContenidoPalet otro) {
        return lote.getCodigo().equals(otro.lote.getCodigo()) && tipoContenido == otro.tipoContenido;
    }

    public void acumular(ContenidoPalet otro) {
        cantidad += otro.cantidad;
    }

    public boolean esMiPalet(Palet palet) {
        return this.palet.getId().equals(palet.getId());
    }
}