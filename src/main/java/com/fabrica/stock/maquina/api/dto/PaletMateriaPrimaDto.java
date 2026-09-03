package com.fabrica.stock.maquina.api.dto;

import com.fabrica.stock.palet.domain.Palet;

public record PaletMateriaPrimaDto(
        Long paletId,
        String matricula,
        String productoDesc,
        String productoCodigo,
        String loteContenido
) {

    public static PaletMateriaPrimaDto of(Palet palet) {
        return new PaletMateriaPrimaDto(
                palet.getId(),
                palet.getMatricula(),
                palet.getProducto().getDescripcion(),
                palet.getProducto().getCodigo().valor(),
                palet.getContenidoGranel().getLote().getCodigo()
        );
    }
}