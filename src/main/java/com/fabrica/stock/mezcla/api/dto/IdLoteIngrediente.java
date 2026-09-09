package com.fabrica.stock.mezcla.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IdLoteIngrediente(
        @NotNull(message = "ID lote es obligatorio.")
        @Positive(message = "ID lote no puede ser menor o igual que 0.")
        Long idLoteIngrediente
) {}