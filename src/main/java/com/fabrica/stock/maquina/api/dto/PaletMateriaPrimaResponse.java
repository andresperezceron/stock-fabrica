package com.fabrica.stock.maquina.api.dto;

import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.ubicacion.domain.Ubicacion;

public record PaletMateriaPrimaResponse(
        Long id,
        String descProducto,
        String codigoLote,
        String nombreUbicacion
)
{
    public static PaletMateriaPrimaResponse of(Palet palet) {
        return new PaletMateriaPrimaResponse(
                palet.getId(),
                palet.getProducto().getDescripcion(),
                palet.getContenidos().get(0).getLote().getCodigo(),
                ((Ubicacion) palet.getLocalizacion()).getNombre().valor()
        );
    }
}