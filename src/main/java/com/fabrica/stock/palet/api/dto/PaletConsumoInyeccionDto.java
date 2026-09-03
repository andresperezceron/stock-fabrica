package com.fabrica.stock.palet.api.dto;

import com.fabrica.stock.palet.domain.Palet;

public record PaletConsumoInyeccionDto(
        Long paletId,
        String matricula,
        String codigoProducto,
        String descProducto,
        String loteMateriaPrima,
        String nombreUbicacion
)
{
    public static PaletConsumoInyeccionDto of(Palet palet) {
        return new PaletConsumoInyeccionDto(
                palet.getId(),
                palet.getMatricula(),
                palet.getProducto().getCodigo().valor(),
                palet.getProducto().getDescripcion(),
                palet.getContenidoGranel().getLote().getCodigo(),
                palet.getNombreUbicacion().valor()
        );
    }
}