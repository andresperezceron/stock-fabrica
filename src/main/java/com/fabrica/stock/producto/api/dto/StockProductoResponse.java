package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.lote.api.dto.StockLoteResponse;

import java.math.BigDecimal;
import java.util.List;

public class StockProductoResponse {

    public Long productoId;

    public String codigo;

    public String descripcion;

    public BigDecimal stockTotal;

    public List<StockLoteResponse> lotes;
}
