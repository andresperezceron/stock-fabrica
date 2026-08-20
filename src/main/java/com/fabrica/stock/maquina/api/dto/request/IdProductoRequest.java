package com.fabrica.stock.maquina.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IdProductoRequest(
        @NotNull(message = "ID producto es obligatorio.")
        @Positive(message = "ID producto no puede ser menor que 0.")
        Long productoId
) {}