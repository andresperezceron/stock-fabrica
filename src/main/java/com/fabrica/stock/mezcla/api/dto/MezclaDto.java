package com.fabrica.stock.mezcla.api.dto;

import com.fabrica.stock.mezcla.domain.Mezcla;

public record MezclaDto(
        Long id,
        Long productoId,
        String productoCodigo,
        String productoDesc

) {
    public static MezclaDto of(Mezcla mezcla) {
        return new MezclaDto(
                mezcla.getId(),
                mezcla.getProducto().getId(),
                mezcla.getProducto().getCodigo().valor(),
                mezcla.getProducto().getDescripcion()
        );
    }
}