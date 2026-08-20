package com.fabrica.stock.palet.api.dto.response;

import com.fabrica.stock.palet.api.dto.ContenidoPaletDto;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;

import java.time.LocalDateTime;
import java.util.List;

public record VerPaletDetalleResponse(
        String matricula,
        String productoDesc,
        List<ContenidoPaletDto> contenidos,
        LocalDateTime fechaCreacion,
        EstadoPalet estado,
        LocalDateTime fechaCambioEstado,
        String nombreUbicacion
) {
    public static VerPaletDetalleResponse of(Palet palet) {
        return new VerPaletDetalleResponse(
                palet.getMatricula(),
                palet.getProducto().getDescripcion(),
                palet.getContenidos().stream().map(ContenidoPaletDto::of).toList(),
                palet.getFechaCreacion(),
                palet.getEstado(),
                palet.getFechaCambioEstado(),
                palet.getNombreUbicacion().valor()
        );
    }
}
