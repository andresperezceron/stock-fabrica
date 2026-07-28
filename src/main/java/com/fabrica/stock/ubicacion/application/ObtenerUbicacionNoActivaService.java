package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.exceptions.UbicacionNoEncontradaException;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerUbicacionNoActivaService {
    private final UbicacionRepository ubicacionRepository;

    public ObtenerUbicacionNoActivaService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public Ubicacion obtener(Long id) {
        return ubicacionRepository
                .findByIdAndActivoFalseAndSistemaFalse(id)
                .orElseThrow(() -> new UbicacionNoEncontradaException("No existe ubicación Inactiva con maquinaId: " + id));
    }

    public Ubicacion obtener(String nombreUbicacion) {
        return ubicacionRepository
                .findByNombreAndActivoFalseAndSistemaFalse(nombreUbicacion)
                .orElseThrow(() ->
                        new UbicacionNoEncontradaException("No existe ubicación Inactiva con nombre: "
                                + nombreUbicacion));
    }
}