package com.fabrica.stock.entradaforanea.api.dto;

import com.fabrica.stock.palet.domain.Palet;

public record PaletCajasVacioResponse(Long id) {
    public static PaletCajasVacioResponse of(Palet palet) {
        return new PaletCajasVacioResponse(palet.getId());
    }
}