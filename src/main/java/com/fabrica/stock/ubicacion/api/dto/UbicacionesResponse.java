package com.fabrica.stock.ubicacion.api.dto;

import com.fabrica.stock.ubicacion.domain.Ubicacion;

import java.util.List;

public record UbicacionesResponse(List<UbicacionResponse> ubicaciones) {
    public static UbicacionesResponse of(List<Ubicacion> ubicaciones) {
        return new UbicacionesResponse(ubicaciones.stream().map(UbicacionResponse::of).toList());
    }
}