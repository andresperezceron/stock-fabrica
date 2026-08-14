package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarUbicacionService {
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final UbicacionRepository ubicacionRepository;

    public ActualizarUbicacionService(ObtenerUbicacionService obtenerUbicacionService,
                                      UbicacionRepository ubicacionRepository)
    {
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.ubicacionRepository = ubicacionRepository;
    }

    @Transactional
    public void actualizar(Long id, UbicacionRequest request) {
        Ubicacion ubicacion = obtenerUbicacionService.obtener(id);
        NombreUbicacion nombreUbicacion = new NombreUbicacion(request.nombre());

        if(ubicacionRepository.existsByNombreAndIdNot(nombreUbicacion, ubicacion.getId()))
            throw new ConflictException("Nombre duplicado.");

        ubicacion.actualizarNombre(nombreUbicacion);
    }
}