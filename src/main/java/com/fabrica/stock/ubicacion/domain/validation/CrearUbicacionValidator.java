package com.fabrica.stock.ubicacion.domain.validation;

import com.fabrica.stock.shared.validation.RequestValidator;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.exceptions.NombreUbicacionDuplicadoException;
import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;

public class CrearUbicacionValidator extends RequestValidator<UbicacionRequest, Ubicacion, UbicacionRepository> {
    public CrearUbicacionValidator(
            UbicacionRequest request,
            UbicacionRepository repository) {

        super(request, repository);
    }

    @Override
    protected void normalize() {
        request.nombre = request.nombre.trim();
    }

    @Override
    protected void validateBusinessRules() {
        if(repository.existsByNombre(request.nombre)) {
            throw new NombreUbicacionDuplicadoException(
                    "Ya existe una ubicación con el nombre: "
                            + request.nombre);
        }
    }
}