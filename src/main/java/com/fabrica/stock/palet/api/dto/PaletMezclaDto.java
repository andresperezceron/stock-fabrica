package com.fabrica.stock.palet.api.dto;

import com.fabrica.stock.palet.domain.Palet;

public record PaletMezclaDto(
        Long paletId,
        String matricula,
        String productoCodigo,
        String productoDesc,
        Integer cantidad,
        String unidadMedida,
        String loteContenido
) {
    public static PaletMezclaDto of(Palet palet) {
        return new PaletMezclaDto(
                palet.getId(),
                palet.getMatricula(),
                palet.getProducto().getCodigo().valor(),
                palet.getProducto().getDescripcion(),
                palet.getContenidoGranel().getCantidad(),
                palet.getProducto().getUnidad().name(),
                palet.getContenidoGranel().getLote().getCodigo()
        );
    }
}