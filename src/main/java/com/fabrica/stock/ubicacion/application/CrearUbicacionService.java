package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearUbicacionService {
    private final UbicacionRepository ubicacionRepository;

    public CrearUbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public void crear(UbicacionRequest request) {
        NombreUbicacion nombreUbicacion = new NombreUbicacion(request.nombre());
        if(ubicacionRepository.existsByNombre(nombreUbicacion))
            throw new ConflictException("Nombre duplicado.");
        ubicacionRepository.save(new Ubicacion(nombreUbicacion));
    }
}