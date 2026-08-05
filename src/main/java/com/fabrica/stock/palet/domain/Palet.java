package com.fabrica.stock.palet.domain;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.shared.domain.Localizacion;
import jakarta.persistence.*;

import java.math.BigDecimal;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoProcesoPalet proceso;

    @ManyToOne(optional = false)
    @JoinColumn(name = "localizacion_id", nullable = false)
    private Localizacion localizacion;

    @OneToMany(mappedBy = "palet")
    private final List<ContenidoPalet> contenidos = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    public Palet(Maquina maquina, TipoPalet tipo, Estacion estacionProduccion) {
        this.localizacion = estacionProduccion;
        this.tipo = tipo;
        this.maquina = maquina;
        estado = EstadoPalet.ABIERTO;
        proceso = EstadoProcesoPalet.INICIADO;
        fechaCreacion = LocalDateTime.now();
    }

    protected Palet() {}

    public Long getId() {
        return id;
    }

    public Localizacion getLocalizacion() { return localizacion; }

    public TipoPalet getTipo() { return tipo; }

    public boolean esTipoGranel() { return tipo == TipoPalet.GRANEL; }

    public boolean esTipoCaja() { return tipo == TipoPalet.CAJA; }

    public Maquina getMaquina() { return maquina; }

    public List<ContenidoPalet> getContenidos() { return contenidos; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public void abrir() { estado = EstadoPalet.ABIERTO; }

    public void cerrar() { estado = EstadoPalet.CERRADO; }

    public void iniciarProceso() { proceso = EstadoProcesoPalet.INICIADO; }

    public void pausarProceso() { proceso = EstadoProcesoPalet.PAUSADO; }

    public void finalizarProceso() { proceso = EstadoProcesoPalet.FINALIZADO; }

    public boolean procesoEstaPausado() { return proceso == EstadoProcesoPalet.PAUSADO; }

    public void cambiarAPendienteValidacion() { estado = EstadoPalet.PENDIENTE_VALIDACION; }

    public boolean estaAbierto() { return estado == EstadoPalet.ABIERTO; }

    public boolean estaCerrado() { return estado == EstadoPalet.CERRADO; }

    public boolean estaPendienteValidacion() { return estado == EstadoPalet.PENDIENTE_VALIDACION; }

    public boolean estaValidado() { return estado == EstadoPalet.VALIDADO; }

    public boolean estaVacio() { return contenidos.isEmpty(); }

    public void moverA(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public ContenidoPalet registrarContenido(Lote lote, BigDecimal cantidad) {
        return new ContenidoPalet(this, lote, cantidad);
    }
}