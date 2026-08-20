package com.fabrica.stock.palet.api.dto.response;

import com.fabrica.stock.palet.api.dto.PaletConsumoInyeccionDto;
import com.fabrica.stock.palet.domain.Palet;

import java.util.List;

public record PaletsConsumoInyeccionResponse(List<PaletConsumoInyeccionDto> palets) {
    public static PaletsConsumoInyeccionResponse of(List<Palet> palets) {
        return new PaletsConsumoInyeccionResponse(palets
                .stream()
                .map(PaletConsumoInyeccionDto::of)
                .toList());
    }
}