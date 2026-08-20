package com.fabrica.stock.maquina.api.dto.request;

import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.validation.constraints.Size;

public record ActualizarObsRequest(
        @Size(
            max = TextoConstraints.LARGO,
            message = "Descripción demasiado larga. Max: " +  TextoConstraints.LARGO
        )
        String observaciones
){}