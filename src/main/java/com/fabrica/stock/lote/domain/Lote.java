package com.fabrica.stock.lote.domain;

import com.fabrica.stock.lote.domain.constraints.LoteConstraints;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lote")
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = LoteConstraints.CODIGO_LOTE)
    private String codigoLote;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigenLote origen;

    @Column(length = LoteConstraints.CODIGO_LOTE_PROVEEDOR)
    private String loteProveedor;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaquinaProduccion maquina;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TurnoProduccion turno;

    protected Lote() {
        // JPA
    }

    public Lote(
            String codigoLote,
            OrigenLote origen,
            String loteProveedor,
            LocalDateTime fechaCreacion,
            MaquinaProduccion maquina,
            TurnoProduccion turno
    ) {
        this.codigoLote = codigoLote;
        this.origen = origen;
        this.loteProveedor = loteProveedor;
        this.fechaCreacion = fechaCreacion;
        this.maquina = maquina;
        this.turno = turno;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public OrigenLote getOrigen() {
        return origen;
    }

    public String getLoteProveedor() {
        return loteProveedor;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public MaquinaProduccion getMaquina() { return maquina; }

    public TurnoProduccion getTurno() { return turno; }
}