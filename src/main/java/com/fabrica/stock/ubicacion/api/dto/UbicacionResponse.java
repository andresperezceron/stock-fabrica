package com.fabrica.stock.ubicacion.api.dto;

import com.fabrica.stock.ubicacion.domain.Ubicacion;

public record UbicacionResponse(
    Long id,
    String nombre
) {
    public static UbicacionResponse of(Ubicacion ubicacion) {
        return new UbicacionResponse(ubicacion.getId(), ubicacion.getNombre().valor());
    }
}