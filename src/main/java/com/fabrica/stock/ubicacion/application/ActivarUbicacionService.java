package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActivarUbicacionService {
    private final ObtenerUbicacionInactivaService obtenerUbicacionInactivaService;

    public ActivarUbicacionService(ObtenerUbicacionInactivaService obtenerUbicacionInactivaService) {
        this.obtenerUbicacionInactivaService = obtenerUbicacionInactivaService;
    }

    @Transactional
    public void activar(Long id) {
        Ubicacion ubicacion = obtenerUbicacionInactivaService.obtener(id);
        ubicacion.activar();
    }
}