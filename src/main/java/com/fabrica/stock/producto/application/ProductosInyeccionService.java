package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.api.dto.ProductosInyeccionResponse;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosInyeccionService {
    private final ProductoRepository productoRepository;

    public ProductosInyeccionService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductosInyeccionResponse listar() {
        return ProductosInyeccionResponse.of(productoRepository
                .findAllByActivoTrueOrderByDescripcionAsc()
                .stream()
                .filter(Producto::produceInyeccion)
                .toList());
    }
}