package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.UnidadMedida;

public record ProductoResponse(
    Long id,
    String codigo,
    String descripcion,
    UnidadMedida unidad,
    Long familiaId,
    int piezasPorCaja
)
{
    public static ProductoResponse of(Producto producto) {
        return new ProductoResponse(
                producto.getId(),
                producto.getCodigo().valor(),
                producto.getDescripcion(),
                producto.getUnidad(),
                producto.getFamilia().getId(),
                producto.getPiezasPorCaja()
        );
    }
}