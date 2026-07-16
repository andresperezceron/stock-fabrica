package com.fabrica.stock.configurador.api.dto;

import com.fabrica.stock.palet.domain.TipoPalet;
import jakarta.validation.constraints.NotNull;

public class NuevoPaletRequest {
    @NotNull
    public Long ubicacionId;
    @NotNull
    public TipoPalet tipoPalet;
}
