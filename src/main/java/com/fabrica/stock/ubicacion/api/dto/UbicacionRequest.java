package com.fabrica.stock.ubicacion.api.dto;

import jakarta.validation.constraints.NotBlank;

public class UbicacionRequest {

    @NotBlank(message = "Nombre Ubicacion es obligatorio.")
    public String nombre;
}
