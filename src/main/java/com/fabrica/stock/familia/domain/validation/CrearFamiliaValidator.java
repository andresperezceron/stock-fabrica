package com.fabrica.stock.familia.domain.validation;

import com.fabrica.stock.familia.api.dto.CrearFamiliaRequest;
import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.familia.domain.exceptions.NombreFamiliaDuplicadoException;
import com.fabrica.stock.familia.infrastructure.FamiliaRepository;
import com.fabrica.stock.shared.domain.validation.RequestValidator;

public class CrearFamiliaValidator extends RequestValidator<CrearFamiliaRequest, Familia, FamiliaRepository> {
    public CrearFamiliaValidator(CrearFamiliaRequest request, FamiliaRepository repository) {
        super(request, repository);
    }

    @Override
    protected void normalize() {
        request.nombre = request.nombre.trim();
    }

    @Override
    protected void validateBusinessRules() {
        if(repository.existsByNombre(request.nombre)){
            throw new NombreFamiliaDuplicadoException("Nombre de familia duplicado.");
        }
    }
}