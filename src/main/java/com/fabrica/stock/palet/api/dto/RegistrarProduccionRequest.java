package com.fabrica.stock.palet.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RegistrarProduccionRequest(

        @NotNull(message = "Maquina es obligatorio.")
        @Positive(message = "ID Maquina no puede ser negativo.")
        Long maquinaId,

        @NotNull(message = "Palet es obligatorio.")
        @Positive(message = "ID palet no puede ser negativo.")
        Long paletId,


        BigDecimal cantidad
) {}