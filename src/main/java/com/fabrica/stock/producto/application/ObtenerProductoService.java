package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.exceptions.ProductoNoEncontradoException;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerProductoService {
    private final ProductoRepository productoRepository;

    public ObtenerProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto obtener(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("No existe Producto con id: " + id));
    }

    public Producto obtener(String codigo) {
        return productoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ProductoNoEncontradoException("No existe Producto con codigo: " + codigo));
    }
}