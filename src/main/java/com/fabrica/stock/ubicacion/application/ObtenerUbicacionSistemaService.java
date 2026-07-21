package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.UbicacionSistema;
import com.fabrica.stock.ubicacion.domain.exceptions.UbicacionNoEncontradaException;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerUbicacionSistemaService {
    private final UbicacionRepository ubicacionRepository;

    public ObtenerUbicacionSistemaService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public Ubicacion obtener(UbicacionSistema ubicacionSistema) {
        return ubicacionRepository.findByNombreAndSistemaTrue(ubicacionSistema.name())
                .orElseThrow(() -> new UbicacionNoEncontradaException("Ubicacion de sistema: "
                        + ubicacionSistema.name() + " no encontrada"));
    }
}