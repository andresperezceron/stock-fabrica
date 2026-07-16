package com.fabrica.stock.palet.domain;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Palet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ubicacion_id", nullable = false)
    private Ubicacion ubicacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPalet tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConfiguracionPalet configuracion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPalet estado;

    @OneToMany(mappedBy = "palet")
    private final List<ContenidoPalet> contenidos = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    public Palet(Ubicacion ubicacion, TipoPalet tipo, ConfiguracionPalet configuracion, EstadoPalet estado) {
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.configuracion = configuracion;
        this.estado = estado;
        fechaCreacion = LocalDateTime.now();
    }

    protected Palet() {}

    public Long getId() {
        return id;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public TipoPalet getTipo() { return tipo; }

    public ConfiguracionPalet getConfiguracion() { return configuracion; }

    public EstadoPalet getEstado() {
        return estado;
    }

    public List<ContenidoPalet> getContenidos() { return contenidos; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public boolean estaAbierto() {
        return estado == EstadoPalet.ABIERTO;
    }

    public boolean estaCerrado() {
        return estado == EstadoPalet.CERRADO;
    }

    public boolean estaFinalizado() { return estado == EstadoPalet.FINALIZADO; }

    public boolean estaValidado() { return estado == EstadoPalet.VALIDADO; }

    public void setEstado(EstadoPalet estado) { this.estado = estado; }

    public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }

    public void incorporarContenidoPalet(ContenidoPalet contenido) { this.contenidos.add(contenido); }
}