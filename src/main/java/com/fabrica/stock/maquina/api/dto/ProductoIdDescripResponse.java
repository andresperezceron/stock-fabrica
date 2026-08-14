package com.fabrica.stock.maquina.api.dto;

import com.fabrica.stock.producto.domain.Producto;

public record ProductoIdDescripResponse(Long id, String descripcion) {
    public static ProductoIdDescripResponse of(Producto producto) {
        return new ProductoIdDescripResponse(producto.getId(),  producto.getDescripcion());
    }
}