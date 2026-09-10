package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.Producto;

public record ProductoInyeccionDto(Long id, String codigo, String descripcion) {
    public static ProductoInyeccionDto of(Producto producto) {
        return new ProductoInyeccionDto(producto.getId(), producto.getCodigo().valor(), producto.getDescripcion());
    }
}