package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.api.dto.ActualizarUbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.validation.ActualizarUbicacionValidator;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarUbicacionService {
    private final UbicacionRepository ubicacionRepository;
    private final ObtenerUbicacionService obtenerUbicacionService;

    public ActualizarUbicacionService(UbicacionRepository ubicacionRepository,
                                      ObtenerUbicacionService obtenerUbicacionService) {
        this.ubicacionRepository = ubicacionRepository;
        this.obtenerUbicacionService = obtenerUbicacionService;
    }

    @Transactional
    public void actualizar(ActualizarUbicacionRequest request) {
        new ActualizarUbicacionValidator(request, ubicacionRepository).validate();
        Ubicacion ubicacion = obtenerUbicacionService.obtener(request.id);
        ubicacion.modificarNombre(request.nombre);
    }
}
