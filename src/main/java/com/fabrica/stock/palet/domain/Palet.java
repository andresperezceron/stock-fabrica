package com.fabrica.stock.palet.domain;

import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.contenidopalet.domain.TipoContenido;
import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.estacion.domain.Estaciones;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.Localizacion;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
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
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPalet estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "localizacion_id", nullable = false)
    private Localizacion localizacion;

    @OneToMany(mappedBy = "palet", orphanRemoval = true)
    private final List<ContenidoPalet> contenidos = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaCambioEstado;

    public Palet(TipoPalet tipo, Producto producto, Estacion produccion) {
        this.tipo = tipo;
        this.producto = producto;
        this.localizacion = produccion;
        estado = EstadoPalet.ABIERTO;
        fechaCreacion = LocalDateTime.now();
    }

    protected Palet() {}

    public Long getId() {
        return id;
    }

    public TipoPalet getTipo() { return tipo; }

    public Producto getProducto() { return producto; }

    public EstadoPalet getEstado() { return estado; }

    public Localizacion getLocalizacion() { return localizacion; }

    public List<ContenidoPalet> getContenidos() { return contenidos; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public LocalDateTime getFechaCambioEstado() { return fechaCambioEstado; }

    public String getMatricula() { return (soyTipoCaja() ? "C" : "G") + id; }

    public NombreUbicacion getNombreUbicacion() {
        if(!(localizacion instanceof Ubicacion ubicacion)) throw new ConflictException("No estoy en una Ubicación.");
        return ubicacion.getNombre();
    }

    public ContenidoPalet getContenidoGranel() {
        if(soyTipoCaja()) throw new RecursoNoValidoException("Servicio no disponible para tipo Caja.");
        return contenidos.get(0);
    }

    public Estacion getEstacion() {
        if(!(localizacion instanceof Estacion)) throw new ConflictException("Estoy en una ubicación.");
        return (Estacion) localizacion;
    }

    public Ubicacion getUbicacion() {
        if(!(localizacion instanceof Ubicacion)) throw new ConflictException("Estoy en una estación.");
        return (Ubicacion) localizacion;
    }

    public void abrir() { cambiarEstado(EstadoPalet.ABIERTO); }

    public void cerrar() { cambiarEstado(EstadoPalet.CERRADO); }

    public void pendienteValidacion() { cambiarEstado(estado = EstadoPalet.PENDIENTE_VALIDACION); }

    public void enConsumo() { cambiarEstado(EstadoPalet.EN_CONSUMO); }

    public void consumido() { cambiarEstado(EstadoPalet.CONSUMIDO); }

    public void validado() { cambiarEstado(EstadoPalet.VALIDADO); }

    public void moverA(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public boolean estoyAbierto() { return estado == EstadoPalet.ABIERTO; }

    public boolean estoyCerrado() { return estado == EstadoPalet.CERRADO; }

    public boolean soyTipoGranel() { return tipo == TipoPalet.GRANEL; }

    public boolean soyTipoCaja() { return tipo == TipoPalet.CAJA; }

    public boolean soyConsumoInyeccion() { return producto.consumeInyeccion() && soyTipoGranel(); }

    public boolean estoyUbicacion() { return localizacion instanceof Ubicacion; }

    public boolean estoyPendienteValidacion() { return estado == EstadoPalet.PENDIENTE_VALIDACION; }

    public boolean estoyValidado() { return estado == EstadoPalet.VALIDADO; }

    public boolean estoyVacio() { return contenidos.isEmpty(); }

    public boolean estoyEstacionProveedor() {
        return localizacion instanceof Estacion estacion && estacion.getNombre().valor().equals(Estaciones.PROVEEDOR);
    }

    public boolean estoyEstacionValidacion() {
        return localizacion instanceof Estacion estacion && estacion.getNombre().valor().equals(Estaciones.VALIDACION);
    }

    public boolean estoyEstacionConsumoInyeccion() {
        return localizacion instanceof Estacion estacion &&
                estacion.getNombre().valor().equals(Estaciones.CONSUMO_INYECCION);
    }

    public boolean tengoPersonalizada() {
        return contenidos.stream()
                .anyMatch(c -> c.getTipo() == TipoContenido.PERSONALIZADA);
    }

    public void modificarContenidoPalet(Integer nuevaCantidad) {
        if(soyTipoCaja()) throw new ConflictException("Servicio no disponible para tipo Caja.");
        getContenidoGranel().modificarCantidad(nuevaCantidad);
    }

    public void consolidarContenidos() {
        if(soyTipoGranel()) return;

        List<ContenidoPalet> consolidados = new ArrayList<>();
        for(ContenidoPalet contenido : contenidos) {
            ContenidoPalet existente = consolidados.stream()
                    .filter(c -> c.esMismoContenido(contenido))
                    .findFirst()
                    .orElse(null);
            if(existente == null) consolidados.add(contenido);
            else existente.acumular(contenido);
        }

        contenidos.clear();
        contenidos.addAll(consolidados);
    }

    public boolean registroSuperaCapacidad(TipoContenido tipoContenido, Integer cantidad, ConfigProduccion config) {
        switch(tipoContenido) {
            case COMPLETA, PERSONALIZADA -> { return (capacidadOcupada() + cantidad) > config.getCajasPorPalet(); }
            case MEDIA -> { return (cantidad / 2 + capacidadOcupada()) > config.getCajasPorPalet(); }
            case GRANEL -> { return false; }
            default -> throw new RecursoNoValidoException("Tipo Caja no valido.");
        }
    }

    private int capacidadOcupada() {
        int completas = contenidos.stream()
                .filter(c -> c.getTipo() == TipoContenido.COMPLETA)
                .mapToInt(ContenidoPalet::getCantidad)
                .sum();
        int medias = contenidos.stream()
                .filter(c -> c.getTipo() == TipoContenido.MEDIA)
                .mapToInt(ContenidoPalet::getCantidad)
                .sum();
        return completas + medias / 2;
    }

    private void cambiarEstado(EstadoPalet estado) {
        this.estado = estado;
        this.fechaCambioEstado = LocalDateTime.now();
    }
}