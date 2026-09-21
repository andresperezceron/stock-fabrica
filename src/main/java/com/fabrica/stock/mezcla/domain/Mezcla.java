package com.fabrica.stock.mezcla.domain;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mezcla")
public class Mezcla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToMany
    @JoinTable(
            name = "mezcla_lote",
            joinColumns = @JoinColumn(name = "mezcla_id"),
            inverseJoinColumns = @JoinColumn(name = "lote_id")
    )
    private List<Lote> lotesMateriaPrima = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    protected Mezcla() {}

    public Mezcla(Producto producto, List<Lote> lotesMateriaPrima) {
        this.producto = producto;
        this.lotesMateriaPrima = lotesMateriaPrima;
        fechaCreacion = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public Producto getProducto() { return producto; }

    public List<Lote> getLotesMateriaPrima() { return lotesMateriaPrima; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public boolean contieneMateriaPrima(Lote lote) {
        return lotesMateriaPrima.stream().anyMatch(l -> l.getId().equals(lote.getId()));
    }

    public void agregarIngrediente(Lote lote) {
        lotesMateriaPrima.add(lote);
    }

    public void quitarIngrediente(Lote lote) {
        lotesMateriaPrima.remove(lote);
    }
}