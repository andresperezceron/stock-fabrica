package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.UnidadMedida;
import com.fabrica.stock.producto.domain.constraints.ProductoConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CrearProductoRequest {

    @NotBlank(message = "Codigo producto es obligatorio.")
    @Size(max = ProductoConstraints.CODIGO)
    public String codigo;

    @NotBlank(message = "Descripcion producto es obligatorio")
    @Size(max = ProductoConstraints.DESCRIPCION)
    public String descripcion;

    @NotNull(message = "Unidad no puede ser nula.")
    public UnidadMedida unidad;

    @NotBlank(message = "Familia producto es obligatorio.")
    @Size(max = ProductoConstraints.FAMILIA)
    public String familia;
}
