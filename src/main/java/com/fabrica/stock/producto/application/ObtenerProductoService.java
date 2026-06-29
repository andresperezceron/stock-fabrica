package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.domain.RecursoNoEncontradoException;

public class ObtenerProductoService {
    private final ProductoRepository productoRepository;

    public ObtenerProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto obtener(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Recurso no encontrado: " + id));
    }
}
