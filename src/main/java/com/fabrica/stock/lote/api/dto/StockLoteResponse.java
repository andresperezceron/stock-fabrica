package com.fabrica.stock.lote.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockLoteResponse {
    public Long loteId;
    public String codigoLote;
    public LocalDateTime fechaCreacion;
    public BigDecimal stock;
}
