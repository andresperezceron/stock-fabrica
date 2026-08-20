package com.fabrica.stock.palet.api.dto;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.contenidopalet.domain.TipoContenido;
import com.fabrica.stock.producto.domain.UnidadMedida;

import java.time.LocalDateTime;
import java.util.List;

public record ContenidoPaletDto(
        TipoContenido tipo,
        int cantidad,
        UnidadMedida unidadMedida,
        LocalDateTime fechaCreacion,
        List<MateriaPrimaLoteDto> lotesMateriasPrimas
) {
    public static ContenidoPaletDto of(ContenidoPalet contenido) {
        return new ContenidoPaletDto(
                contenido.getTipo(),
                contenido.getCantidad(),
                contenido.getPalet().getProducto().getUnidad(),
                contenido.getFechaCreacion(),
                contenido.getLotesMateriaPrima().stream().map(MateriaPrimaLoteDto::of).toList()
        );
    }
}