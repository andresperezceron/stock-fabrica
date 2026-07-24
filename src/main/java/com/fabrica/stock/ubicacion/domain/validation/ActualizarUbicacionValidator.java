package com.fabrica.stock.ubicacion.domain.validation;

import com.fabrica.stock.shared.validation.RequestValidator;
import com.fabrica.stock.ubicacion.api.dto.ActualizarUbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.exceptions.NombreUbicacionDuplicadoException;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;

public class ActualizarUbicacionValidator extends
        RequestValidator<ActualizarUbicacionRequest, Ubicacion, UbicacionRepository> {
    private final Long ubicacionId;

    public ActualizarUbicacionValidator(
            ActualizarUbicacionRequest request,
            UbicacionRepository repository,
            Long ubicacionId)
    {
        super(request, repository);
        this.ubicacionId = ubicacionId;
    }

    @Override
    protected void normalize() {
        request.nombre = request.nombre.trim().toUpperCase();
    }

    @Override
    protected void validateBusinessRules() {
        if(repository.existsByNombreAndIdNot(request.nombre, ubicacionId)) {
            throw new NombreUbicacionDuplicadoException("Nombre ubicacion duplicado");
        }
    }
}