package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.*;

public record DtoProducto(
    Long id,
    String codigo,
    String descripcion,
    UnidadMedida unidad,
    Long familiaId,
    int piezasPorCaja,
    TipoProducto tipo,
    OrigenProducto origen,
    DestinoProducto destino
)
{
    public static DtoProducto of(Producto producto) {
        return new DtoProducto(
                producto.getId(),
                producto.getCodigo().valor(),
                producto.getDescripcion(),
                producto.getUnidad(),
                producto.getFamilia().getId(),
                producto.getPiezasPorCaja(),
                producto.getTipo(),
                producto.getOrigen(),
                producto.getDestino()
        );
    }
}