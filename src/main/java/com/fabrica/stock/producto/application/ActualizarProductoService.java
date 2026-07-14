package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ActualizarProductoService {
    private final ProductoRepository productoRepository;

    public ActualizarProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public void actualizar(Long id, ProductoRequest request) {

    }
}
