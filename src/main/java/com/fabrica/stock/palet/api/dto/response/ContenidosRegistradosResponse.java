package com.fabrica.stock.palet.api.dto.response;

import com.fabrica.stock.palet.api.dto.ContenidoRegistradoDto;
import com.fabrica.stock.palet.domain.Palet;

import java.util.List;

public record ContenidosRegistradosResponse(List<ContenidoRegistradoDto> contenidos) {
    public static ContenidosRegistradosResponse of(Palet palet) {
        return new ContenidosRegistradosResponse(
                palet.getContenidos()
                .stream()
                .map(ContenidoRegistradoDto::of)
                .toList()
        );
    }
}