package com.fabrica.stock.palet.api.dto;

import jakarta.validation.constraints.NotNull;

public class CrearPaletRequest {
    @NotNull
    public Long ubicacionId;
}
