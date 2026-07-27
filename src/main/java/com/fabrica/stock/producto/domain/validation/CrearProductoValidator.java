package com.fabrica.stock.producto.domain.validation;

import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.producto.domain.exceptions.ProductoCodigoDuplicadoException;
import com.fabrica.stock.shared.domain.validation.RequestValidator;

public class CrearProductoValidator extends RequestValidator<ProductoRequest, Producto, ProductoRepository> {
    public CrearProductoValidator(ProductoRequest request, ProductoRepository repository) {
        super(request, repository);
    }

    @Override
    protected void validateBusinessRules() {
        if(repository.existsByCodigo(new CodigoProducto(request.codigo()))) {
            throw new ProductoCodigoDuplicadoException("Código producto duplicado.");
        }
    }
}