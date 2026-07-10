package com.fabrica.stock.movimiento.api.dto;

import jakarta.validation.constraints.NotNull;

public class ConsultarTrazabilidadLoteRequest {
    @NotNull
    public Long loteId;
}
