package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.Producto;

import java.util.List;

public record ProductosInyeccionResponse(List<ProductoInyeccionDto> productosInyeccion) {
    public static ProductosInyeccionResponse of(List<Producto> productos) {
        return new ProductosInyeccionResponse(productos
                .stream()
                .map(ProductoInyeccionDto::of)
                .toList());
    }
}