package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.OrigenProducto;
import com.fabrica.stock.producto.domain.DestinoProducto;
import com.fabrica.stock.producto.domain.TipoProducto;
import com.fabrica.stock.producto.domain.UnidadMedida;
import com.fabrica.stock.producto.domain.constraints.ProductoConstraints;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductoRequest {
    @NotBlank(message = "Código producto es obligatorio.")
    @Size(
            max = ProductoConstraints.CODIGO, min = ProductoConstraints.CODIGO,
            message = "Código solo puede ser seis dígitos."
    )
    public String codigo;

    @NotBlank(message = "Descripción producto es obligatorio")
    @Size(
            max = TextoConstraints.MEDIO,
            message = "Descripción demasiado larga. Max: " +  TextoConstraints.MEDIO
    )
    public String descripcion;

    @NotNull(message = "Unidad no puede ser nula.")
    public UnidadMedida unidad;

    @NotNull(message = "Familia producto es obligatorio.")
    public Long familia_id;

    @NotNull(message = "Piezas por caja es obligatorio.")
    @Min(0)
    public Integer piezasPorCaja;

    @NotNull(message = "Tipo de producto es obligatorio.")
    public TipoProducto tipo;

    @NotNull(message = "Origen de producto es obligatorio.")
    public OrigenProducto origen;

    @NotNull(message = "Proceso fabricación de producto es obligatorio.")
    public DestinoProducto destino;
}