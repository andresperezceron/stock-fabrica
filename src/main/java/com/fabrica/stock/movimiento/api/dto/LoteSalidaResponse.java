package com.fabrica.stock.movimiento.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoteSalidaResponse {

    public Long loteId;

    public String codigoLote;

    public LocalDateTime fechaCreacion;

    public BigDecimal stockDisponible;

    public BigDecimal cantidadAsignada;
}
