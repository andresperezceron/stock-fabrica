package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.api.dto.CrearProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.validation.CrearProductoValidator;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearProductoService {
    private final ProductoRepository productoRepository;

    public CrearProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto ejecutar(CrearProductoRequest request) {
        new CrearProductoValidator(request, productoRepository).validate();
        return productoRepository.save(
                new Producto(
                request.codigo,
                request.descripcion,
                request.unidad,
                request.familia
        ));
    }
}