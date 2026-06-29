package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.constraints.ProductoConstraints;
import com.fabrica.stock.producto.domain.validation.CrearProductoValidator;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.producto.domain.exceptions.CodigoProductoDuplicadoException;
import com.fabrica.stock.shared.domain.CodigoProductoIsBlanckException;
import com.fabrica.stock.shared.domain.CodigoProductoMaxLengthException;
import com.fabrica.stock.shared.domain.CodigoProductoNullException;
import org.springframework.stereotype.Service;

@Service
public class CrearProductoService {
    private final ProductoRepository productoRepository;

    public CrearProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto ejecutar(ProductoRequest request) {
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