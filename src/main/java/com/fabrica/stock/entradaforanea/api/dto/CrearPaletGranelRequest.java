package com.fabrica.stock.entradaforanea.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearPaletGranelRequest(
        @NotNull(message = "ID producto es obligatorio.")
        @Positive(message = "ID producto debe ser positivo.")
        Long productoId,

        @NotBlank(message = "Lote proveedor es obligatorio.")
        String loteProveedor,

        @NotNull(message = "Cantidad es obligatoria.")
        @Positive(message = "Cantidad debe ser positiva.")
        Integer cantidad
) {}