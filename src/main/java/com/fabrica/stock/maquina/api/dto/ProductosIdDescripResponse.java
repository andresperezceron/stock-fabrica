package com.fabrica.stock.maquina.api.dto;

import com.fabrica.stock.producto.domain.Producto;

import java.util.List;

public record ProductosIdDescripResponse(List<ProductoIdDescripResponse> productos) {
    public static ProductosIdDescripResponse of(List<Producto> productos) {
        return new ProductosIdDescripResponse(productos
                .stream()
                .map(ProductoIdDescripResponse::of)
                .toList());
    }
}