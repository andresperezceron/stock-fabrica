package com.fabrica.stock.ubicacion.api.dto;

import com.fabrica.stock.ubicacion.domain.Ubicacion;

public class UbicacionResponse {
    private final Long id;
    private final String nombre;

    private UbicacionResponse(Ubicacion ubicacion) {
        id = ubicacion.getId();
        nombre = ubicacion.getNombre();
    }

    public static UbicacionResponse of(Ubicacion ubicacion) {
        return new UbicacionResponse(ubicacion);
    }

    public Long getId() { return id; }

    public String getNombre() { return nombre; }
}