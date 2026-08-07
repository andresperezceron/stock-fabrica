package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearUbicacionService {
    private final UbicacionRepository ubicacionRepository;
    private final ValidarNombreUbicacionService validarNombreUbicacionService;

    public CrearUbicacionService(UbicacionRepository ubicacionRepository,
                                 ValidarNombreUbicacionService validarNombreUbicacionService)
    {
        this.ubicacionRepository = ubicacionRepository;
        this.validarNombreUbicacionService = validarNombreUbicacionService;
    }

    public void crear(UbicacionRequest request) {
        ubicacionRepository.save(new Ubicacion(validarNombreUbicacionService.validar(request.nombre())));
    }
}