package com.fabrica.stock.ubicacion.domain.validation;

import com.fabrica.stock.shared.validation.RequestValidator;
import com.fabrica.stock.ubicacion.api.dto.ModificarUbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.exceptions.NombreUbicacionDuplicadoException;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;

import java.util.Optional;

public class ModificarUbicacionValidator extends
        RequestValidator<ModificarUbicacionRequest, Ubicacion, UbicacionRepository> {
    public ModificarUbicacionValidator(ModificarUbicacionRequest request, UbicacionRepository repository) {
        super(request, repository);
    }

    @Override
    protected void normalize() {
        request.nombreModificado = request.nombreModificado.trim();
    }

    @Override
    protected void validateBusinessRules() {
        Optional<Ubicacion> existente = repository.findByNombre(request.nombreModificado);
        if(existente.isPresent() && !existente.get().getId().equals(request.ubicacionId)) {
            throw new NombreUbicacionDuplicadoException("Nombre ubicacion duplicado");
        }
    }
}