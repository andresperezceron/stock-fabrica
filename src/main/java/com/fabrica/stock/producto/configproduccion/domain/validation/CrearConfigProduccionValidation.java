package com.fabrica.stock.producto.configproduccion.domain.validation;

import com.fabrica.stock.producto.configproduccion.api.dto.CrearConfigProduccionRequest;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ConfigProduccionDuplicadaException;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.LoteMateriaPrimaDuplicadoException;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.exceptions.ProductoNoEncontradoException;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.validation.RequestValidator;

public class CrearConfigProduccionValidation extends
        RequestValidator<CrearConfigProduccionRequest, ConfigProduccion, ConfigProduccionRepository> {
    private final ProductoRepository productoRepository;

    public CrearConfigProduccionValidation(
            CrearConfigProduccionRequest request,
            ConfigProduccionRepository repository,
            ProductoRepository productoRepository)
    {
        super(request, repository);
        this.productoRepository = productoRepository;
    }

    @Override
    protected void validateBusinessRules() {
        if(productoRepository.existsById(request.productoId)) {
            throw new ProductoNoEncontradoException("No existe el producto con el id: " + request.productoId);
        }

        if(repository.existsConfigProduccionByProductoId(request.productoId)) {
            throw new ConfigProduccionDuplicadaException("Ya existe un configuracion para este producto");
        }

        if(request.lotesMateriaPrima.stream().distinct().count() != request.lotesMateriaPrima.size()) {
            throw new LoteMateriaPrimaDuplicadoException("Lotes materia prima duplicados");
        }
    }
}