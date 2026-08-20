package com.fabrica.stock.palet.api.dto.response;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.palet.api.dto.MateriaPrimaContenidoDto;

import java.time.LocalDateTime;
import java.util.List;

public record ContenidoRegistradoResponse(
        String productoDesc,
        String loteCodigo,
        int cantidad,
        int piezasPorCaja,
        List<MateriaPrimaContenidoDto> materiasPrimas,
        LocalDateTime fechaCreacion
) {
    public static ContenidoRegistradoResponse of(ContenidoPalet contenido) {
        return new ContenidoRegistradoResponse(
                contenido.getLote().getProducto().getDescripcion(),
                contenido.getLote().getCodigo(),
                contenido.getCantidad(),
                contenido.getLote().getProducto().getPiezasPorCaja(),
                contenido.getLotesMateriaPrima().stream().map(MateriaPrimaContenidoDto::of).toList(),
                contenido.getFechaCreacion()
        );
    }
}