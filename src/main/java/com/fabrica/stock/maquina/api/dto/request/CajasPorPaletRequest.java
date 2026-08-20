package com.fabrica.stock.maquina.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CajasPorPaletRequest(
        @NotNull(message = "Cajas por palet es obligatorio.")
        @Positive(message = "Cajas por palet debe ser mayor que 0.")
        Integer cajasPorPalet
) {}