package com.fabrica.stock.configurador.api.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class FinalizarPaletRequest {
    @NotNull
    public Long paletId;

    @NotNull
    public Long loteId;

    @NotNull
    public BigDecimal cantidad;
}