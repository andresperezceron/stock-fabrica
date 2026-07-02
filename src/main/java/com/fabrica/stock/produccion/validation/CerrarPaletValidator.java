package com.fabrica.stock.produccion.validation;

import com.fabrica.stock.produccion.api.dto.CerrarPaletRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.validation.RequestValidator;

public class CerrarPaletValidator extends RequestValidator<CerrarPaletRequest, Producto, ProductoRepository> {

    public CerrarPaletValidator(CerrarPaletRequest request, ProductoRepository repository) {
        super(request, repository);
    }

    @Override
    protected void normalize() {
        request.loteProveedor = request.loteProveedor != null ? request.loteProveedor.trim() : null;
    }

    @Override
    protected void validateBusinessRules() {

    }
}
