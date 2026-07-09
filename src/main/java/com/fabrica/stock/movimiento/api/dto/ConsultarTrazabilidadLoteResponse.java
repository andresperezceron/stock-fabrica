package com.fabrica.stock.movimiento.api.dto;

import java.util.List;

public class ConsultarTrazabilidadLoteResponse {
    public String codigoLote;
    public String producto;
    public List<MovimientoResponse> movimientos;
}
