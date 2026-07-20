package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.exceptions.UbicacionNoEncontradaException;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerUbicacionService {
    private final UbicacionRepository ubicacionRepository;

    public ObtenerUbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public Ubicacion obtener(Long id) {
        return ubicacionRepository
                .findByIdAndActivoTrue(id)
                .orElseThrow(() -> new UbicacionNoEncontradaException("No existe ubicacion con id: " + id));
    }

    public Ubicacion obtener(String nombreUbicacion) {
        return ubicacionRepository
                .findByNombreAndActivoTrue(nombreUbicacion)
                .orElseThrow(() ->
                        new UbicacionNoEncontradaException("No existe ubicacion con nombre: " + nombreUbicacion));
    }
}