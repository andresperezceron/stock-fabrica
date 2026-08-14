package com.fabrica.stock.configproduccion.api.dto;

import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.validation.constraints.Size;

public record ObsConfigProduccionRequest(
        @Size(
            max = TextoConstraints.LARGO,
            message = "Descripción demasiado larga. Max: " +  TextoConstraints.LARGO
        )
        String observaciones
){}