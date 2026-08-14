package com.fabrica.stock.palet.api.dto;

import com.fabrica.stock.palet.domain.TipoCaja;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record RegistrarProduccionRequest(
        @NotNull(message = "Maquina es obligatorio.")
        @Positive(message = "ID Maquina no puede ser negativo.")
        Long maquinaId,

        @NotNull(message = "Palet es obligatorio.")
        @Positive(message = "ID palet no puede ser negativo.")
        Long paletId,

        @NotNull
        TipoCaja tipo,

        @NotNull(message = "Cantidad es obligatoria.")
        @Positive(message = "Cantidad no puede ser menor de 0.")
        Integer cantidad
) {}