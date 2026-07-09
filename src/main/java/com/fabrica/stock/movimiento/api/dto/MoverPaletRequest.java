package com.fabrica.stock.movimiento.api.dto;

import jakarta.validation.constraints.NotNull;

public class MoverPaletRequest {
    @NotNull
    public Long paletId;

    @NotNull
    public Long destinoId;
}