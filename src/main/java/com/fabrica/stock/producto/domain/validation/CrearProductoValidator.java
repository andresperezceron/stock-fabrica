package com.fabrica.stock.producto.domain.validation;

import com.fabrica.stock.familia.domain.exceptions.FamiliaNoEncontradaException;
import com.fabrica.stock.familia.infrastructure.FamiliaRepository;
import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.exceptions.CodigoProductoNoNumericoException;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.producto.domain.exceptions.CodigoProductoDuplicadoException;
import com.fabrica.stock.shared.validation.RequestValidator;

public class CrearProductoValidator extends RequestValidator<ProductoRequest, Producto, ProductoRepository> {
    private final FamiliaRepository familiaRepository;

    public CrearProductoValidator(ProductoRequest request,
                                  ProductoRepository repository,
                                  FamiliaRepository familiaRepository)
    {
        super(request, repository);
        this.familiaRepository = familiaRepository;
    }

    @Override
    protected void normalize() {
        request.codigo = request.codigo.trim();
        request.descripcion = request.descripcion.trim();
    }

    @Override
    protected void validateBusinessRules() {
        if(repository.existsByCodigo(request.codigo)) {
            throw new CodigoProductoDuplicadoException("Código producto duplicado.");
        }

        if(!request.codigo.matches("\\d+")) {
            throw new CodigoProductoNoNumericoException("Código producto solo puede ser seis dígitos.");
        }

        if(!familiaRepository.existsById(request.familia_id)) {
            throw new FamiliaNoEncontradaException("No existe familia con id: " + request.familia_id);
        }
    }
}