package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.exceptions.UbicacionNoEncontradaException;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerUbicacionInactivaService {
    private final UbicacionRepository ubicacionRepository;

    public ObtenerUbicacionInactivaService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public Ubicacion obtener(Long id) {
        return ubicacionRepository
                .findByIdAndActivoFalse(id)
                .orElseThrow(() -> new UbicacionNoEncontradaException("No existe ubicacion Inactiva con id: " + id));
    }

    public Ubicacion obtener(String nombreUbicacion) {
        return ubicacionRepository
                .findByNombreAndActivoFalse(nombreUbicacion)
                .orElseThrow(() ->
                        new UbicacionNoEncontradaException("No existe ubicacion Inactiva con nombre: "
                                + nombreUbicacion));
    }
}
