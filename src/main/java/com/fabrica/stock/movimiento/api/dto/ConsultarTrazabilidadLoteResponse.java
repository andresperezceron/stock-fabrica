package com.fabrica.stock.movimiento.api.dto;

import com.fabrica.stock.lote.domain.Lote;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultarTrazabilidadLoteResponse {
    public String codigoLote;
    public String maquina;
    public String turno;
    public LocalDateTime fechaProduccion;
    public List<MovimientoResponse> movimientos;

    public ConsultarTrazabilidadLoteResponse(Lote lote, List<MovimientoResponse> movimientos) {
        this.codigoLote = lote.getCodigoLote();
        this.maquina = lote.getMaquina().getNombre().valor();
        this.fechaProduccion = lote.getFechaCreacion();
        this.turno = lote.getTurno().getCodigo();
        this.movimientos = movimientos;
    }
}