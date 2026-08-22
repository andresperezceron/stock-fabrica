package com.fabrica.stock.movimiento.domain;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.Localizacion;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movimiento_stock")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contenido_id")
    private ContenidoPalet contenido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimiento tipo;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origen_id")
    private Localizacion origen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destino_id")
    private Localizacion destino;

    protected Movimiento() {}

    public Movimiento(ContenidoPalet contenido,
                      TipoMovimiento tipo,
                      Localizacion origen,
                      Localizacion destino)
    {
        this.contenido = contenido;
        this.tipo = tipo;
        this.origen = origen;
        this.destino = destino;
        fecha = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public ContenidoPalet getContenido() { return contenido; }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public LocalDateTime getFecha() { return fecha; }

    public Localizacion getOrigen() { return origen; }

    public Localizacion getDestino() { return destino; }

    public static List<Movimiento> primerMovimiento(Palet palet, Estacion produccion, Estacion validacion) {
        return crearMovimientos(palet, TipoMovimiento.ENTRADA, produccion, validacion);
    }

    public static List<Movimiento> paraConsumo(Palet palet, Ubicacion ubicacion, Estacion produccion) {
        return crearMovimientos(palet, TipoMovimiento.PARA_CONSUMICION, ubicacion, produccion);
    }

    public static List<Movimiento> consumicion(Palet palet, Estacion produccion, Estacion consumicion) {
        return crearMovimientos(palet, TipoMovimiento.CONSUMICION, produccion, consumicion);
    }

    public static List<Movimiento> entradaForanea(Palet palet, Estacion proveedor, Estacion validacion) {
        return crearMovimientos(palet, TipoMovimiento.ENTRADA, proveedor, validacion);
    }

    private static List<Movimiento> crearMovimientos(
            Palet palet,
            TipoMovimiento tipo,
            Localizacion origen,
            Localizacion destino)
    {
        List<Movimiento> movimientos = new ArrayList<>();
        for(ContenidoPalet contenido : palet.getContenidos())
            movimientos.add(new Movimiento(contenido, tipo,  origen, destino));
        return movimientos;
    }
}