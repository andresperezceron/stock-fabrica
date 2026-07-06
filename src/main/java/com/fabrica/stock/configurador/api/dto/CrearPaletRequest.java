package com.fabrica.stock.configurador.api.dto;

import jakarta.validation.constraints.NotNull;

public class CrearPaletRequest {
    @NotNull
    public Long ubicacionId;
}
