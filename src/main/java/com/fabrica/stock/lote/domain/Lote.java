package com.fabrica.stock.lote.domain;

import com.fabrica.stock.lote.domain.vo.CodigoLote;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lote")
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private CodigoLote codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigenLote origen;

    @Column(length = TextoConstraints.CORTO)
    private String loteProveedor;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maquina_id")
    private Maquina maquina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TurnoProduccion turno;

    protected Lote() {}

    public Lote(
            CodigoLote codigo,
            OrigenLote origen,
            String loteProveedor,
            LocalDateTime fechaCreacion,
            Maquina maquina,
            Producto producto,
            TurnoProduccion turno
    ) {
        this.codigo = codigo;
        this.origen = origen;
        this.loteProveedor = loteProveedor;
        this.fechaCreacion = fechaCreacion;
        this.maquina = maquina;
        this.producto = producto;
        this.turno = turno;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() { return loteProveedor == null ? codigo.valor() : loteProveedor; }

    public OrigenLote getOrigen() {
        return origen;
    }

    public String getLoteProveedor() {
        return loteProveedor;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Maquina getMaquina() { return maquina; }

    public Producto getProducto() { return producto; }

    public TurnoProduccion getTurno() { return turno; }
}