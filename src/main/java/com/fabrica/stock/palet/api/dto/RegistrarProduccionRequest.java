package com.fabrica.stock.palet.api.dto;

public record RegistrarProduccionRequest(
        Long maquinaId,
        Long paletId
) {
}