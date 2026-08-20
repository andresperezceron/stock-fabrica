package com.fabrica.stock.palet.api.dto;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.contenidopalet.domain.TipoContenido;
import com.fabrica.stock.producto.domain.UnidadMedida;

import java.time.LocalDateTime;

public record ContenidoRegistradoDto(
        TipoContenido tipo,
        int cantidad,
        UnidadMedida unidadMedida,
        LocalDateTime fechaCreacion
) {
    public static ContenidoRegistradoDto of(ContenidoPalet contenido) {
        return new ContenidoRegistradoDto(
                contenido.getTipo(),
                contenido.getCantidad(),
                contenido.getPalet().getProducto().getUnidad(),
                contenido.getFechaCreacion()
        );
    }
}