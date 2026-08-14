package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.OrigenProducto;
import com.fabrica.stock.producto.domain.DestinoProducto;
import com.fabrica.stock.producto.domain.TipoProducto;
import com.fabrica.stock.producto.domain.UnidadMedida;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.validation.constraints.*;

public record ProductoRequest(
    @NotBlank(message = "Código producto es obligatorio.")
    String codigo,

    @NotBlank(message = "Descripción producto es obligatorio")
    @Size(
            max = TextoConstraints.MEDIO,
            message = "Descripción demasiado larga. Max: " +  TextoConstraints.MEDIO
    )
    String descripcion,

    @NotNull(message = "Unidad no puede ser nula.")
    UnidadMedida unidad,

    @NotNull(message = "Familia producto es obligatorio.")
    @Positive(message = "ID familia no puede ser negativo.")
    Long familia_id,

    @NotNull(message = "Piezas por caja es obligatorio.")
    @PositiveOrZero
    Integer piezasPorCaja,

    @NotNull(message = "Tipo de producto es obligatorio.")
    TipoProducto tipo,

    @NotNull(message = "Origen de producto es obligatorio.")
    OrigenProducto origen,

    @NotNull(message = "Proceso fabricación de producto es obligatorio.")
    DestinoProducto destino
){}