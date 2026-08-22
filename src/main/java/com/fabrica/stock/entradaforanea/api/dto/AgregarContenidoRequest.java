package com.fabrica.stock.entradaforanea.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AgregarContenidoRequest(
        @NotNull(message = "ID palet es obligatorio.")
        @Positive(message = "ID palet debe ser positivo.")
        Long paletId,

        @NotBlank(message = "Lote proveedor es obligatorio.")
        String loteProveedor,

        @NotNull(message = "Cantidad es obligatoria.")
        @Positive(message = "Cantidad debe ser positiva.")
        Integer cantidad
) {}