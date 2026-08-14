package com.fabrica.stock.configproduccion.domain;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "config_produccion")
public class ConfigProduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @OneToOne(optional = false)
    @JoinColumn(name = "producto_id", unique = true)
    private Producto producto;

    @Column(nullable = false)
    private int cajasPorPalet;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaUltimaModificacion;

    @ManyToMany
    @JoinTable(
            name = "config_produccion_palet",
            joinColumns = @JoinColumn(name = "config_produccion_id"),
            inverseJoinColumns = @JoinColumn(name = "palet_id")
    )
    private final List<Palet> materiasPrimas = new ArrayList<>();

    @Column(length = TextoConstraints.LARGO)
    private String observaciones;

    protected ConfigProduccion() {}

    public ConfigProduccion(Producto producto) {
        this.producto = producto;
        cajasPorPalet = 1;
        observaciones = "";
    }

    public Long getId() { return id; }

    public Producto getProducto() { return producto; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public LocalDateTime getFechaUltimaModificacion() {  return fechaUltimaModificacion; }

    public List<Palet> getMateriasPrimas() { return Collections.unmodifiableList(materiasPrimas); }

    public int getCajasPorPalet() { return cajasPorPalet; }

    public String getObservaciones() { return observaciones; }

    public void agregarMateriaPrima(Palet palet) { materiasPrimas.add(palet); }

    public void consumirMateriaPrima(Palet palet) { materiasPrimas.remove(palet); }

    public void actualizarCajasPorPalet(int cajasPorPalet) {  this.cajasPorPalet = cajasPorPalet; }

    public void actualizarObservaciones(String observaciones) { this.observaciones = observaciones; }

    public boolean esValida() { return !materiasPrimas.isEmpty() && cajasPorPalet > 0; }

    public boolean contieneMateriaPrima(Palet palet) {
        return materiasPrimas.stream().anyMatch(p -> p.getId().equals(palet.getId()));
    }

    public List<Lote> obtenerLotesMateriaPrima() {
        return materiasPrimas
                .stream()
                .flatMap(p -> p.getContenidos().stream())
                .map(ContenidoPalet::getLote)
                .toList();
    }

    @PrePersist
    void alCrear() {
        LocalDateTime ahora = LocalDateTime.now();
        fechaCreacion = ahora;
        fechaUltimaModificacion = ahora;
    }

    @PreUpdate
    void alActualizar() {
        fechaUltimaModificacion = LocalDateTime.now();
    }
}