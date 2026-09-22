package com.fabrica.stock.mezcla.api.dto;

import com.fabrica.stock.mezcla.domain.Mezcla;

public record ComposicionResponse(
        String matricula,
        String productoCodigo,
        String productoDesc,
        String loteCodigo
) {}
