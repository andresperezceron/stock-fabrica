package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.Producto;

public record DtoProductoInyeccion(Long id, String codigo, String descripcion) {
    public static DtoProductoInyeccion of(Producto producto) {
        return new DtoProductoInyeccion(producto.getId(), producto.getCodigo().valor(), producto.getDescripcion());
    }
}