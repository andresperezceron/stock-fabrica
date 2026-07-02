package com.fabrica.stock.produccion.api.dto;

import java.math.BigDecimal;

public class CerrarPaletRequest {
    public String maquina;
    public Long productoId;
    public BigDecimal cantidad;
}
