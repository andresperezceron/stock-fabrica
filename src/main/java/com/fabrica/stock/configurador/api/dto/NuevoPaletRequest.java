package com.fabrica.stock.configurador.api.dto;

import jakarta.validation.constraints.NotNull;

public class NuevoPaletRequest {
    @NotNull
    public Long ubicacionId;
}
