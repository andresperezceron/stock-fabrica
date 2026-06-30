package com.fabrica.stock.producto.domain.validation;

import com.fabrica.stock.producto.api.dto.CrearProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.producto.domain.exceptions.CodigoProductoDuplicadoException;
import com.fabrica.stock.shared.validation.RequestValidator;

public class CrearProductoValidator extends RequestValidator<CrearProductoRequest, Producto, ProductoRepository> {
    public CrearProductoValidator(CrearProductoRequest request, ProductoRepository repository) {
        super(request, repository);
    }

    @Override
    protected void normalize() {
        request.codigo = request.codigo.trim();
        request.descripcion = request.descripcion.trim();
        request.familia = request.familia.trim();
    }

    @Override
    protected void validateBusinessRules() {
        if(repository.existsByCodigo(request.codigo)) {
            throw new CodigoProductoDuplicadoException("Codigo producto duplicado.");
        }
    }
}