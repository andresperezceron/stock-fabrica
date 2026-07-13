package com.fabrica.stock.movimiento.api.dto;

import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import jakarta.validation.constraints.NotNull;

public class ReubicarPaletRequest {
    @NotNull
    public Long paletId;

    @NotNull
    public TipoMovimiento tipoMovimiento;

    @NotNull
    public Long destinoId;
}