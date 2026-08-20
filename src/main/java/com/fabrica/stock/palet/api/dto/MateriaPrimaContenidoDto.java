package com.fabrica.stock.palet.api.dto;

import com.fabrica.stock.lote.domain.Lote;

public record MateriaPrimaContenidoDto(
        String loteCodigo,
        String productoDesc
) {
    public static MateriaPrimaContenidoDto of(Lote lote) {
        return new MateriaPrimaContenidoDto(
                lote.getProducto().getDescripcion(),
                lote.getCodigo()
        );
    }
}
