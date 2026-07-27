package com.fabrica.stock.producto.domain.validation;

import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.exceptions.ProductoCodigoDuplicadoException;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.domain.validation.RequestValidator;

public class ActualizarProductoValidator extends RequestValidator<ProductoRequest, Producto, ProductoRepository> {
    private final Long productoId;

    public ActualizarProductoValidator(ProductoRequest request, ProductoRepository repository, Long productoId) {
        super(request, repository);
        this.productoId = productoId;
    }

    @Override
    protected void validateBusinessRules() {
        if(repository.existsByCodigoAndIdNot(new CodigoProducto(request.codigo()), productoId)) {
            throw new ProductoCodigoDuplicadoException("Código duplicado");
        }
    }
}