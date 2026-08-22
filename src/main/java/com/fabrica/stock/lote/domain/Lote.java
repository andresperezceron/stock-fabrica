package com.fabrica.stock.lote.domain;

import com.fabrica.stock.lote.domain.vo.CodigoLote;
import com.fabrica.stock.maquina.domain.vo.NombreMaquina;
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

    @Column(length = TextoConstraints.CORTO)
    private String loteProveedor;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column
    private NombreMaquina nombreMaquina;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TurnoProduccion turno;

    protected Lote() {}

    public Lote(
            CodigoLote codigo,
            String loteProveedor,
            LocalDateTime fechaCreacion,
            NombreMaquina nombreMaquina,
            Producto producto,
            TurnoProduccion turno
    ) {
        this.codigo = codigo;
        this.loteProveedor = loteProveedor;
        this.fechaCreacion = fechaCreacion;
        this.nombreMaquina = nombreMaquina;
        this.producto = producto;
        this.turno = turno;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() { return loteProveedor == null ? codigo.valor() : loteProveedor; }

    public String getLoteProveedor() { return loteProveedor; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public Producto getProducto() { return producto; }

    public NombreMaquina getNombreMaquina() { return nombreMaquina; }

    public TurnoProduccion getTurno() { return turno; }
}