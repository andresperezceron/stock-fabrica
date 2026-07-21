package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.Producto;

import java.util.List;

public class ProductosResponse {
    private final List<ProductoResponse> productos;

    private ProductosResponse(List<ProductoResponse> productos) {
        this.productos = productos;
    }

    public static ProductosResponse of(List<Producto> productos) {
        return new ProductosResponse(productos
                .stream()
                .map(ProductoResponse::of)
                .toList());
    }

    public List<ProductoResponse> getProductos() { return productos; }
}