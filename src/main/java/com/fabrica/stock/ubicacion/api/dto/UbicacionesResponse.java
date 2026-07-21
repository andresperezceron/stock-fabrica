package com.fabrica.stock.ubicacion.api.dto;

import com.fabrica.stock.ubicacion.domain.Ubicacion;

import java.util.List;

public class UbicacionesResponse {
    private final List<Ubicacion> ubicaciones;

    private UbicacionesResponse(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public static UbicacionesResponse of(List<Ubicacion> ubicaciones) {
        return new UbicacionesResponse(ubicaciones);
    }

    public List<Ubicacion> getUbicaciones() { return ubicaciones; }
}