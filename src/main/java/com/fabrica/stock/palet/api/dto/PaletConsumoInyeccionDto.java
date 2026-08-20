package com.fabrica.stock.palet.api.dto;

import com.fabrica.stock.palet.domain.Palet;

public record PaletConsumoInyeccionDto(
        String matricula,
        String descProducto,
        String loteMateriaPrima,
        String nombreUbicacion
)
{
    public static PaletConsumoInyeccionDto of(Palet palet) {
        return new PaletConsumoInyeccionDto(
                palet.getMatricula(),
                palet.getProducto().getDescripcion(),
                palet.getContenidoGranel().getLote().getCodigo(),
                palet.getNombreUbicacion().valor()
        );
    }
}