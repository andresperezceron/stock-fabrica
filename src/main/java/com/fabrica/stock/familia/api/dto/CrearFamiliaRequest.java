package com.fabrica.stock.familia.api.dto;

import com.fabrica.stock.familia.domain.constraints.FamiliaConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CrearFamiliaRequest {
    @NotBlank(message = "Nombre Familia es obligatorio.")
    @Size(
            max = FamiliaConstraints.NOMBRE,
            message = "Nombre demasiado largo. Max: " + FamiliaConstraints.NOMBRE
    )
    public String nombre;
}