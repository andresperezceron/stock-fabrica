package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.UnidadMedida;
import com.fabrica.stock.producto.domain.constraints.ProductoConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductoRequest {
    @NotBlank(message = "Codigo producto es obligatorio.")
    @Size(
            max = ProductoConstraints.CODIGO, min = ProductoConstraints.CODIGO,
            message = "Codigo solo puede ser seis dígitos."
    )
    public String codigo;

    @NotBlank(message = "Descripcion producto es obligatorio")
    @Size(
            max = ProductoConstraints.DESCRIPCION,
            message = "Descripcion demasiado larga. Max: " +  ProductoConstraints.DESCRIPCION
    )
    public String descripcion;

    @NotNull(message = "Unidad no puede ser nula.")
    public UnidadMedida unidad;

    @NotNull(message = "Familia producto es obligatorio.")
    public Long familia_id;
}