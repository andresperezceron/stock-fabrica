package com.fabrica.stock.maquina.api.dto;

import com.fabrica.stock.palet.domain.TipoPalet;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IniciarProduccionRequest(
        @NotNull(message = "Máquina es obligatorio.")
        @Positive(message = "ID máquina no puede ser negativo.")
        Long maquinaId,

        @NotNull(message = "Tipo de palet es obligatorio.")
        TipoPalet tipoPalet
) {}