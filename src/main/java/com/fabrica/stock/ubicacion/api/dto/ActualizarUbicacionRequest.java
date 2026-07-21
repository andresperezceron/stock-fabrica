package com.fabrica.stock.ubicacion.api.dto;

import jakarta.validation.constraints.NotBlank;

public class ActualizarUbicacionRequest {
    @NotBlank
    public String nombre;
}