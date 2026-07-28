package com.fabrica.stock.palet.domain;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.UbicacionSistema;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Palet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPalet tipo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maquina_id")
    private Maquina maquina;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPalet estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estacion_id", nullable = false)
    private Estacion estacion;

    @OneToMany(mappedBy = "palet")
    private final List<ContenidoPalet> contenidos = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    public Palet(Maquina maquina, TipoPalet tipo) {
        this.estacion = maquina.getEstacion();
        this.tipo = tipo;
        this.maquina = maquina;
        this.estado = EstadoPalet.ABIERTO;
        fechaCreacion = LocalDateTime.now();
    }

    protected Palet() {}

    public Long getId() {
        return id;
    }

    public Estacion getEstacion() { return estacion; }

    public TipoPalet getTipo() { return tipo; }

    public EstadoPalet getEstado() { return estado; }

    public List<ContenidoPalet> getContenidos() { return contenidos; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public boolean estaAbierto() { return estado == EstadoPalet.ABIERTO; }

    public boolean estaCerrado() { return estado == EstadoPalet.CERRADO; }

    public boolean estaFinalizado() { return estado == EstadoPalet.FINALIZADO; }

    public boolean estaValidado() { return estado == EstadoPalet.VALIDADO; }

    public void setEstado(EstadoPalet estado) { this.estado = estado; }

    public void incorporarContenidoPalet(ContenidoPalet contenido) { this.contenidos.add(contenido); }
}