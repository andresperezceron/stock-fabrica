package com.fabrica.stock.palet.domain;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.Localizacion;
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

    public Palet(TipoPalet tipo, Maquina maquina, Producto producto, Estacion produccion) {
        this.tipo = tipo;
        this.maquina = maquina;
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

    public Maquina getMaquina() { return maquina; }

    public Producto getProducto() { return producto; }

    public Localizacion getLocalizacion() { return localizacion; }

    public List<ContenidoPalet> getContenidos() { return contenidos; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public String getMatricula() { return (soyTipoCaja() ? "C" : "G") + id; }

    public void abrir() { estado = EstadoPalet.ABIERTO; }

    public void cerrar() { estado = EstadoPalet.CERRADO; }

    public void pendienteValidacion() { estado = EstadoPalet.PENDIENTE_VALIDACION; }

    public void enConsumo() { estado = EstadoPalet.EN_CONSUMO; }

    public void consumido() { estado = EstadoPalet.CONSUMIDO; }

    public void moverA(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public boolean estaAbierto() { return estado == EstadoPalet.ABIERTO; }

    public boolean estaCerrado() { return estado == EstadoPalet.CERRADO; }

    public boolean soyTipoGranel() { return tipo == TipoPalet.GRANEL; }

    public boolean soyTipoCaja() { return tipo == TipoPalet.CAJA; }

    public boolean soyMateriaPrima() {
        return producto.soyMateriaPrima() && estado == EstadoPalet.VALIDADO;
    }

    public boolean estoyPendienteValidacion() { return estado == EstadoPalet.PENDIENTE_VALIDACION; }

    public boolean estoyValidado() { return estado == EstadoPalet.VALIDADO; }

    public boolean estoyVacio() { return contenidos.isEmpty(); }

    public int capacidadOcupada() {
        int completas = contenidos.stream()
                .filter(c -> c.getTipoCaja() == TipoCaja.COMPLETA)
                .mapToInt(ContenidoPalet::getCantidad)
                .sum();

        int medias = contenidos.stream()
                .filter(c -> c.getTipoCaja() == TipoCaja.MEDIA)
                .mapToInt(ContenidoPalet::getCantidad)
                .sum();

        return completas + medias / 2;
    }

    public void consolidarContenidos() {
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

    public boolean tengoPersonalizada() {
        return contenidos.stream()
                .anyMatch(c -> c.getTipoCaja() == TipoCaja.PERSONALIZADA);
    }
}