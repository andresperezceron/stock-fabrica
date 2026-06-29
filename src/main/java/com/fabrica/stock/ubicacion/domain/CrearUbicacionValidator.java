package com.fabrica.stock.ubicacion.domain;

import com.fabrica.stock.shared.domain.NombreUbicacionDuplicadoException;
import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.api.validation.UbicacionRequestValidator;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;

public class CrearUbicacionValidator extends UbicacionRequestValidator<UbicacionRequest> {

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
    protected void validateRules() {

        if(repository.existsByNombre(request.nombre)) {

            throw new NombreUbicacionDuplicadoException(
                    "Ya existe una ubicación con el nombre: "
                            + request.nombre);
        }
    }
}