package com.fabrica.stock.palet.api.dto;

import com.fabrica.stock.palet.domain.ConfiguracionPalet;
import jakarta.validation.constraints.NotNull;

public class AbrirPaletRequest {
    @NotNull
    public Long ubicacionId;

    @NotNull
    public ConfiguracionPalet configuracion;
    public Integer piezasPorCaja;
    public Integer cajasPorPalet;
}
