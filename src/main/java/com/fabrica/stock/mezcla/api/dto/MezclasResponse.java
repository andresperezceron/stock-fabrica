package com.fabrica.stock.mezcla.api.dto;

import com.fabrica.stock.mezcla.domain.Mezcla;

import java.util.List;

public record MezclasResponse(List<MezclaDto> mezclas) {
    public static MezclasResponse of(List<Mezcla> mezclas) {
        return new MezclasResponse(mezclas
                .stream()
                .map(MezclaDto::of)
                .toList()
        );
    }
}