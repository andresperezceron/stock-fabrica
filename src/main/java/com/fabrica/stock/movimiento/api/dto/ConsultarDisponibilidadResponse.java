package com.fabrica.stock.movimiento.api.dto;


import com.fabrica.stock.lote.api.dto.StockLoteResponse;

import java.math.BigDecimal;
import java.util.List;

public class ConsultarDisponibilidadResponse {
    public Long productoId;
    public String codigo;
    public BigDecimal stockTotal;
    public List<StockLoteResponse> lotes;
}
