package com.fabrica.stock.mezcla.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IdPaletIngrediente(
        @NotNull(message = "ID palet es obligatorio.")
        @Positive(message = "ID palet no puede ser menor o igual que 0.")
        Long idPaletIngrediente
) {}