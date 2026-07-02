package com.fabrica.stock.produccion.api.dto;

import java.math.BigDecimal;

public class CerrarPaletRequest {
    public Long productoId;
    public BigDecimal cantidad;
    public String maquina;
}
