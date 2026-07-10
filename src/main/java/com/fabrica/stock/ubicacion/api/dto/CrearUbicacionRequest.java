package com.fabrica.stock.ubicacion.api.dto;

import com.fabrica.stock.ubicacion.domain.constraints.UbicacionConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CrearUbicacionRequest {
    @NotBlank(message = "Nombre Ubicacion es obligatorio.")
    @Size(max = UbicacionConstraints.NOMBRE)
    public String nombre;
}
