package com.fabrica.stock.producto.configproduccion.domain;

import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

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

    @ManyToMany
    @JoinTable(
            name = "config_produccion_producto",
            joinColumns = @JoinColumn(name = "config_produccion_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private final List<Producto> materiasPrimas = new ArrayList<>();

    @Column(nullable = false, length = TextoConstraints.LARGO)
    String observaciones;

    @Column(nullable = false)
    private boolean activo = true;

    protected ConfigProduccion() {}

    public ConfigProduccion(Producto producto) {
        this.producto = producto;
        this.observaciones = "";
    }

    public Long getId() { return id; }

    public Producto getProducto() { return producto; }

    public List<Producto> getMateriasPrimas() { return Collections.unmodifiableList(materiasPrimas); }

    public String getObservaciones() { return observaciones; }

    public void agregarMateriaPrima(Producto producto) { materiasPrimas.add(producto); }

    public void quitarMateriaPrima(Producto producto) { materiasPrimas.remove(producto); }

    public boolean estaActivo() { return activo; }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() { this.activo = true; }
}