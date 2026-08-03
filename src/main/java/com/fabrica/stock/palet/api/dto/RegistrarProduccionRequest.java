package com.fabrica.stock.palet.api.dto;

import java.math.BigDecimal;

public record RegistrarProduccionRequest(
        Long maquinaId,
        Long paletId,
        BigDecimal cantidad
) {
}