package com.fabrica.stock.maquina.api.dto.request;

import com.fabrica.stock.maquina.domain.EstadoMaquina;
import jakarta.validation.constraints.NotNull;

public record EstadoMaquinaRequest(
        @NotNull(message = "Estado es obligatorio.")
        EstadoMaquina estado
){}