package com.fabrica.stock.ubicacion.api.dto;

import com.fabrica.stock.ubicacion.domain.Ubicacion;

import java.util.List;

public class UbicacionesResponse {
    private final List<UbicacionResponse> ubicaciones;

    private UbicacionesResponse(List<UbicacionResponse> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public static UbicacionesResponse of(List<Ubicacion> ubicaciones) {
        return new UbicacionesResponse(ubicaciones
                .stream()
                .map(UbicacionResponse::of)
                .toList()
        );
    }

    public List<UbicacionResponse> getUbicaciones() { return ubicaciones; }
}