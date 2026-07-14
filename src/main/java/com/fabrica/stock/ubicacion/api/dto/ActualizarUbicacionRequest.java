package com.fabrica.stock.ubicacion.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ActualizarUbicacionRequest {
    @NotNull
    public Long id;

    @NotBlank
    public String nombre;
}