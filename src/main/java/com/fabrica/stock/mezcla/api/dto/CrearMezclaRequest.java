package com.fabrica.stock.mezcla.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CrearMezclaRequest(
        @NotNull(message = "Producto es obligatorio.")
        @Positive(message = "Id producto debe ser mayor que 0.")
        Long productoId,

        @NotEmpty(message = "Lista lotes es obligatoria.")
        List<@NotNull @Positive Long> paletsId
){}