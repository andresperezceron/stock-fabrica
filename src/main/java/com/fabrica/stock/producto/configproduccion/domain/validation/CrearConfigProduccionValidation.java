package com.fabrica.stock.producto.configproduccion.domain.validation;

import com.fabrica.stock.producto.configproduccion.api.dto.CrearConfigProduccionRequest;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ConfigProduccionDuplicadaException;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.LoteMateriaPrimaDupicadoException;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.exceptions.ProductoNoActivoException;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.validation.RequestValidator;

public class CrearConfigProduccionValidation extends
        RequestValidator<CrearConfigProduccionRequest, ConfigProduccion, ConfigProduccionRepository> {
    private final ProductoRepository productoRepository;

    public CrearConfigProduccionValidation(CrearConfigProduccionRequest request,
                                           ConfigProduccionRepository repository,
                                           ProductoRepository productoRepository) {
        super(request, repository);
        this.productoRepository = productoRepository;
    }

    @Override
    protected void validateBusinessRules() {
        if(repository.existsConfigProduccionByProductoId(request.productoId)) {
            throw new ConfigProduccionDuplicadaException("Ya existe un configuracion para este producto");
        }

        if(productoRepository.existsByIsActivoById(request.productoId)) {
            throw new ProductoNoActivoException("Producto fuera de servio.");
        }

        if(request.lotesMateriaPrima.stream().distinct().count() != request.lotesMateriaPrima.size()) {
            throw new LoteMateriaPrimaDupicadoException("Lotes materia prima duplicados");
        }
    }
}