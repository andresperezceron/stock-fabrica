package com.fabrica.stock.palet.api.dto.response;

import com.fabrica.stock.palet.api.dto.PaletMezclaDto;
import com.fabrica.stock.palet.domain.Palet;

import java.util.List;

public record PaletsMezclaResponse(List<PaletMezclaDto> paletsMezcla) {
    public static PaletsMezclaResponse of(List<Palet>  palets) {
        return new PaletsMezclaResponse(palets
                .stream()
                .map(PaletMezclaDto::of)
                .toList()
        );
    }
}