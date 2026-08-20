package com.fabrica.stock.maquina.api.dto.request;

import com.fabrica.stock.contenidopalet.domain.TipoContenido;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegistrarProduccionRequest(
        @NotNull(message = "Tipo contenido es obligatorio.")
        TipoContenido tipoContenido,

        @NotNull(message = "Cantidad es obligatoria.")
        @Positive(message = "Cantidad no puede ser menor de 0.")
        Integer cantidad
) {}