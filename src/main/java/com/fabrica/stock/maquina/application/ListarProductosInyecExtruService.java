package com.fabrica.stock.maquina.application;

import com.fabrica.stock.producto.api.dto.ProductosInyecExtruResponse;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ListarProductosInyecExtruService {
    private final ProductoRepository productoRepository;

    public ListarProductosInyecExtruService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductosInyecExtruResponse listar() {
        return new ProductosInyecExtruResponse(productoRepository.obtenerProductosInyeccionYExtrusion());
    }
}