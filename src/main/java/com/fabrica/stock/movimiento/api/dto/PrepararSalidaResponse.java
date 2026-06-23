package com.fabrica.stock.movimiento.api.dto;

import java.math.BigDecimal;
import java.util.List;

public class PrepararSalidaResponse {
    public Long productoId;
    public String codigo;
    public BigDecimal cantidadSolicitada;
    public BigDecimal stockTotal;
    public Boolean stockSuficiente;
    public List<LoteSalidaResponse> lotes;
}
