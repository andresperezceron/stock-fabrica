package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.Producto;

import java.util.List;

public record ProductosInyeccionResponse(List<DtoProductoInyeccion> productos) {
    public static ProductosInyeccionResponse of(List<Producto> productos) {
        return new ProductosInyeccionResponse(productos
                .stream()
                .map(DtoProductoInyeccion::of)
                .toList());
    }
}