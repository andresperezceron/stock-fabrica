package com.fabrica.stock.palet.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MaquinaIdPaletIdRequest(
        @NotNull(message = "Maquina es obligatorio.")
        @Positive(message = "ID Maquina no puede ser negativo.")
        Long maquinaId,

        @NotNull(message = "Palet es obligatorio.")
        @Positive(message = "ID palet no puede ser negativo.")
        Long paletId
)
{}