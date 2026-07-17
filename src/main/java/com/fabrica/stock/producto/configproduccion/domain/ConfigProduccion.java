package com.fabrica.stock.producto.configproduccion.domain;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "config_produccion")
public class ConfigProduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "producto_id", unique = true)
    private Producto producto;

    @ManyToMany
    @JoinTable(
            name = "config_produccion_lote",
            joinColumns = @JoinColumn(name = "config_produccion_id"),
            inverseJoinColumns = @JoinColumn(name = "lote_id")
    )
    private final List<Lote> lotesMateriaPrima = new ArrayList<>();

    @Column(nullable = false, length = TextoConstraints.LARGO)
    String observaciones;

    protected ConfigProduccion() {}

    public ConfigProduccion(Producto producto, List<Lote> lotesMateriaPrima, String observaciones) {
        this.producto = producto;
        this.lotesMateriaPrima.addAll(lotesMateriaPrima);
        this.observaciones = observaciones;
    }

    public Long getId() { return id; }

    public Producto getProducto() { return producto; }

    public List<Lote> getLotesMateriaPrima() { return lotesMateriaPrima; }

    public String getObservaciones() { return observaciones; }
}