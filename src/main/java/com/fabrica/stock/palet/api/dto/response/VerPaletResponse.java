package com.fabrica.stock.palet.api.dto.response;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.palet.api.dto.ContenidoPaletDto;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;

import java.time.LocalDateTime;
import java.util.List;

public record VerPaletResponse(
        String matricula,
        String productoDesc,
        List<ContenidoPaletDto> contenidos,
        LocalDateTime fechaCreacion,
        EstadoPalet estado,
        LocalDateTime fechaCambioEstado,
        String nombreUbicacion
) {
    public static VerPaletResponse of(Palet palet, List<ContenidoPalet> consolidados) {
        return new VerPaletResponse(
                palet.getMatricula(),
                palet.getProducto().getDescripcion(),
                consolidados.stream().map(ContenidoPaletDto::of).toList(),
                palet.getFechaCreacion(),
                palet.getEstado(),
                palet.getFechaCambioEstado(),
                palet.getNombreUbicacion().valor()
        );
    }
}