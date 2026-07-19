package com.fabrica.stock.producto.configproduccion.domain.validation;

import com.fabrica.stock.producto.configproduccion.api.dto.ActualizarConfigProduccionRequest;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.LoteMateriaPrimaDupicadoException;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.shared.validation.RequestValidator;

public class ActualizarConfigProduccionValidator
        extends RequestValidator<ActualizarConfigProduccionRequest, ConfigProduccion, ConfigProduccionRepository> {

    public ActualizarConfigProduccionValidator(
            ActualizarConfigProduccionRequest request,
            ConfigProduccionRepository repository) {
        super(request, repository);
    }

    @Override
    protected void validateBusinessRules() {
        if(request.lotesMateriaPrima.stream().distinct().count() != request.lotesMateriaPrima.size()) {
            throw new LoteMateriaPrimaDupicadoException("Lotes materia prima duplicados");
        }
    }
}