package com.fabrica.stock.paletizador.api.dto;

import com.fabrica.stock.lote.domain.MaquinaProduccion;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CerrarPaletRequest {
    @NotNull
    public Long paletId;

    @NotNull
    public Long productoId;

    @NotNull
    public BigDecimal cantidad;

    @NotNull
    public MaquinaProduccion maquina;
}