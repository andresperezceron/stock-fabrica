package com.fabrica.stock.ubicacion.api.dto;

import jakarta.validation.constraints.NotBlank;

public record ActualizarUbicacionRequest(
    @NotBlank
    String nombre
){}