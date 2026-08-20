package com.fabrica.stock.palet.api.dto;

import com.fabrica.stock.lote.domain.Lote;

public record MateriaPrimaLoteDto(
        String productoCodigo,
        String productoDesc,
        String loteCodigo
) {
    public static MateriaPrimaLoteDto of(Lote lote) {
        return new MateriaPrimaLoteDto(
                lote.getProducto().getCodigo().valor(),
                lote.getProducto().getDescripcion(),
                lote.getCodigo()
        );
    }
}