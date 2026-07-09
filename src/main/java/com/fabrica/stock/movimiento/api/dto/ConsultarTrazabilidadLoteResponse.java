package com.fabrica.stock.movimiento.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultarTrazabilidadLoteResponse {
    public String codigoLote;
    public String maquina;
    public LocalDateTime fechaProduccion;
    public String turno;
    public List<MovimientoResponse> movimientos;
}
