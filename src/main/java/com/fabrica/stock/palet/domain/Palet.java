package com.fabrica.stock.palet.domain;

import com.fabrica.stock.contenidoPalet.ContenidoPalet;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Palet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "ubicacion_id",
            nullable = false
    )
    private Ubicacion ubicacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConfiguracionPalet configuracion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPalet estado = EstadoPalet.ABIERTO;

    @OneToMany(mappedBy = "palet")
    private final List<ContenidoPalet> contenidos = new ArrayList<>();

    public Palet(Ubicacion ubicacion, EstadoPalet estado) {
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public EstadoPalet getEstado() {
        return estado;
    }

    public List<ContenidoPalet> getContenidos() {
        return contenidos;
    }
}