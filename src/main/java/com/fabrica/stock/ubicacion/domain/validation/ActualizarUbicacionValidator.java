package com.fabrica.stock.ubicacion.domain.validation;

import com.fabrica.stock.shared.domain.validation.RequestValidator;
import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.exceptions.NombreUbicacionDuplicadoException;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;

public class ActualizarUbicacionValidator extends
        RequestValidator<UbicacionRequest, Ubicacion, UbicacionRepository> {
    private final Long ubicacionId;

    public ActualizarUbicacionValidator(
            UbicacionRequest request,
            UbicacionRepository repository,
            Long ubicacionId)
    {
        super(request, repository);
        this.ubicacionId = ubicacionId;
    }

    @Override
    protected void validateBusinessRules() {
        NombreUbicacion nombre = NombreUbicacion.of(request.nombre());
        if(repository.existsByNombreAndIdNot(nombre.valor(), ubicacionId)) {
            throw new NombreUbicacionDuplicadoException("Nombre ubicacion duplicado");
        }
    }
}