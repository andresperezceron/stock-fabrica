package com.fabrica.stock.maquina.api.dto;

import com.fabrica.stock.palet.domain.Palet;

import java.util.List;

public record PaletsMateriaPrimaResponse(List<PaletMateriaPrimaResponse> palets) {
    public static PaletsMateriaPrimaResponse of(List<Palet> palets) {
        return new PaletsMateriaPrimaResponse(palets
                .stream()
                .map(PaletMateriaPrimaResponse::of)
                .toList());
    }
}