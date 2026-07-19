package com.fabrica.stock.producto.configproduccion.api.dto;

import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ActualizarConfigProduccionRequest {
    @NotNull
    public Long id;

    @NotBlank
    @Size(
            max = TextoConstraints.LARGO,
            message = "Observaciones demasiado largo. Max: " +  TextoConstraints.LARGO
    )
    public String observaciones;

    @NotEmpty
    public List<Long> lotesMateriaPrima;
}