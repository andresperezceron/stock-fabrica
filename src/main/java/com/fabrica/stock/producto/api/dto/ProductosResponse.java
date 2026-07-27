package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.Producto;

import java.util.List;

public record ProductosResponse(List<ProductoResponse> productos) {
    public static ProductosResponse of(List<Producto> productos) {
        return new ProductosResponse(productos
                .stream()
                .map(ProductoResponse::of)
                .toList());
    }
}