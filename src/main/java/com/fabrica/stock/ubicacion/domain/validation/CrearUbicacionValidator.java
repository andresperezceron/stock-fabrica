package com.fabrica.stock.ubicacion.domain.validation;

import com.fabrica.stock.shared.domain.validation.RequestValidator;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.exceptions.NombreUbicacionDuplicadoException;
import com.fabrica.stock.ubicacion.api.dto.CrearUbicacionRequest;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;

public class CrearUbicacionValidator extends RequestValidator<CrearUbicacionRequest, Ubicacion, UbicacionRepository> {
    public CrearUbicacionValidator(CrearUbicacionRequest request, UbicacionRepository repository) {
        super(request, repository);
    }

    @Override
    protected void validateBusinessRules() {
        NombreUbicacion nombre = NombreUbicacion.of(request.nombre());
        if(repository.existsByNombre(nombre.valor())) {
            throw new NombreUbicacionDuplicadoException("Ya existe una ubicación con el nombre: " + nombre.valor());
        }
    }
}