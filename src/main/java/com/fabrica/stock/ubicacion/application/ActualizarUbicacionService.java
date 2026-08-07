package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.validation.ActualizarUbicacionValidator;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
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
    public void actualizar(Long id, UbicacionRequest request) {
        new ActualizarUbicacionValidator(request, ubicacionRepository, id).validate();
        Ubicacion ubicacion = obtenerUbicacionService.obtener(id);
        ubicacion.modificarNombre(NombreUbicacion.of(request.nombre()));
    }
}