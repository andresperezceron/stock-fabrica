package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.api.dto.CrearUbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.validation.CrearUbicacionValidator;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearUbicacionService {
    private final UbicacionRepository ubicacionRepository;

    public CrearUbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public void crear(CrearUbicacionRequest request) {
        new CrearUbicacionValidator(request, ubicacionRepository).validate();
        ubicacionRepository.save(new Ubicacion(NombreUbicacion.of(request.nombre())));
    }
}