package com.fabrica.stock.maquina.api.dto.request;

import com.fabrica.stock.palet.domain.TipoPalet;
import jakarta.validation.constraints.NotNull;

public record IniciarProduccionRequest(
        @NotNull(message = "Tipo de palet es obligatorio.")
        TipoPalet tipoPalet
) {}