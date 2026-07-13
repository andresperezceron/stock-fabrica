package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.api.dto.ModificarUbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.validation.ModificarUbicacionValidator;
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
    public Ubicacion actualizar(ModificarUbicacionRequest request) {
        new ModificarUbicacionValidator(request, ubicacionRepository).validate();
        Ubicacion ubicacion = obtenerUbicacionService.obtener(request.ubicacionId);
        ubicacion.setNombre(request.nombreModificado);
        return ubicacion;
    }
}
