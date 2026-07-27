package com.fabrica.stock.producto.configproduccion.domain;

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

    @OneToOne(optional = false)
    @JoinColumn(name = "producto_id", unique = true)
    private Producto producto;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaUltimaModificacion;

    @ManyToMany
    @JoinTable(
            name = "config_produccion_producto",
            joinColumns = @JoinColumn(name = "config_produccion_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private final List<Producto> materiasPrimas = new ArrayList<>();

    @Column(nullable = false, length = TextoConstraints.LARGO)
    private String observaciones;

    protected ConfigProduccion() {}

    public ConfigProduccion(Producto producto) {
        this.producto = producto;
        this.observaciones = "";
    }

    public Long getId() { return id; }

    public Producto getProducto() { return producto; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public LocalDateTime getFechaUltimaModificacion() {  return fechaUltimaModificacion; }

    public List<Producto> getMateriasPrimas() { return Collections.unmodifiableList(materiasPrimas); }

    public String getObservaciones() { return observaciones; }

    public void agregarMateriaPrima(Producto producto) { materiasPrimas.add(producto); }

    public void quitarMateriaPrima(Producto producto) { materiasPrimas.remove(producto); }

    public void actualizarObservaciones(String observaciones) { this.observaciones = observaciones; }

    public boolean esValida() {
        return !materiasPrimas.isEmpty();
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