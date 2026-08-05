package com.fabrica.stock.movimiento.domain;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.Localizacion;
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

    public static List<Movimiento> primerMovimiento(Palet palet, Estacion origen, Estacion destino) {
        return crearMovimientos(palet, TipoMovimiento.ENTRADA, origen, destino);
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