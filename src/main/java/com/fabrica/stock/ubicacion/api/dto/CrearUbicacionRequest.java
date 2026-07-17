package com.fabrica.stock.ubicacion.api.dto;

import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CrearUbicacionRequest {
    @NotBlank(message = "Nombre Ubicacion es obligatorio.")
    @Size(
            max = TextoConstraints.CORTO,
            message = "Nombre demasiado largo. Max: " +  TextoConstraints.CORTO
    )
    public String nombre;
}
