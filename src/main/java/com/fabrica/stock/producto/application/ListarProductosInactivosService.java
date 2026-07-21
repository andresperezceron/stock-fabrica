package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarProductosInactivosService {
    private final ProductoRepository productoRepository;

    public ListarProductosInactivosService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listar() {
        return productoRepository.findAllByActivoFalseOrderByDescripcionDesc();
    }
}