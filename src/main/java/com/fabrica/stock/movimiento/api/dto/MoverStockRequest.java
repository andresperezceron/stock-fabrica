package com.fabrica.stock.movimiento.api.dto;

import java.math.BigDecimal;

public class MoverStockRequest {
    public Long loteId;
    public BigDecimal cantidad;
    public Long origenId;
    public Long destinoId;
}
