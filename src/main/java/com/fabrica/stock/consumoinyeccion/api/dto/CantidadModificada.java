package com.fabrica.stock.consumoinyeccion.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CantidadModificada(
        @NotNull(message = "Cantidad es obligatoria.")
        @Positive(message = "ID producto no puede ser menor o igual que 0.")
        Integer cantidadModificada
) {}