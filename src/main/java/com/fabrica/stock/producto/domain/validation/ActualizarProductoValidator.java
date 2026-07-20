package com.fabrica.stock.producto.domain.validation;

import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.exceptions.CodigoProductoDuplicadoException;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.validation.RequestValidator;

public class ActualizarProductoValidator extends RequestValidator<ProductoRequest, Producto, ProductoRepository> {
    private final Long productoId;

    public ActualizarProductoValidator(ProductoRequest request,
                                       ProductoRepository repository,
                                       Long productoId)
    {
        super(request, repository);
        this.productoId = productoId;
    }

    @Override
    protected void normalize() {
        request.codigo = request.codigo.trim();
        request.descripcion = request.descripcion.trim();
    }

    @Override
    protected void validateBusinessRules() {
        if(repository.existsByCodigoAndIdNot(request.codigo, productoId)) {
            throw new CodigoProductoDuplicadoException("Codigo duplicado");
        }
    }
}