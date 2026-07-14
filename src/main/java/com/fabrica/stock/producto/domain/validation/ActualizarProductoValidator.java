package com.fabrica.stock.producto.domain.validation;

import com.fabrica.stock.familia.domain.exceptions.FamiliaNoEncontradaException;
import com.fabrica.stock.familia.infrastructure.FamiliaRepository;
import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.exceptions.CodigoProductoDuplicadoException;
import com.fabrica.stock.producto.domain.exceptions.ProductoNoEncontradoException;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.validation.RequestValidator;

public class ActualizarProductoValidator extends RequestValidator<ProductoRequest, Producto, ProductoRepository> {
    private final FamiliaRepository familiaRepository;
    private final Long productoId;

    public ActualizarProductoValidator(ProductoRequest request,
                                       ProductoRepository repository,
                                       FamiliaRepository familiaRepository,
                                       Long productoId)
    {
        super(request, repository);
        this.familiaRepository = familiaRepository;
        this.productoId = productoId;
    }

    @Override
    protected void normalize() {
        request.codigo = request.codigo.trim();
        request.descripcion = request.descripcion.trim();
    }

    @Override
    protected void validateBusinessRules() {
        Producto anteriorProducto = repository.findById(productoId)
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado"));

        boolean cambioCodigo = !anteriorProducto.getCodigo().equals(request.codigo);
        boolean cambioFamilia = !anteriorProducto.getFamilia().getId().equals(request.familia_id);

        if(cambioCodigo && repository.existsByCodigo(request.codigo)) {
            throw new CodigoProductoDuplicadoException("Nuevo codigo producto ya esta en uso.");
        }

        if(cambioFamilia && !familiaRepository.existsById(request.familia_id)) {
            throw new FamiliaNoEncontradaException("Nueva familia no encontrada con id: " +  request.familia_id);
        }
    }
}