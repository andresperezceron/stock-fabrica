package com.fabrica.stock.lote.api.dto;

import java.math.BigDecimal;

public class LoteRequest {
    public String codigoLote;
    public String origenLote;
    public String loteProveedor;
    public Long productoId;
    public BigDecimal cantidadInicial;
    public Long origenId;
    public Long destinoId;
}