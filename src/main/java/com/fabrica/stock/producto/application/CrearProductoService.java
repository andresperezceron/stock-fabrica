package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.domain.CodigoProductoDuplicadoException;
import com.fabrica.stock.shared.domain.CodigoProductoNullException;
import org.springframework.stereotype.Service;

@Service
public class CrearProductoService {
    private final ProductoRepository productoRepository;

    public CrearProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crear(ProductoRequest request) {
        if(request.codigo == null) {
            throw new CodigoProductoNullException("El codigo no puede ser nulo.");
        }

        request.codigo = request.codigo.trim();

        if(request.codigo.isBlank()) {
            throw new CodigoProductoNullException("El codigo es obligatorio.");
        }

        if(productoRepository.existsByCodigo(request.codigo)) {
            throw new CodigoProductoDuplicadoException("Codigo producto duplicado.");
        }

        return productoRepository.save(
                new Producto(
                request.codigo,
                request.descripcion,
                request.unidad,
                request.familia
        ));
    }
}