package com.fabrica.stock.ubicacion.application;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActivarUbicacionService {
    private final ObtenerUbicacionNoActivaService obtenerUbicacionNoActivaService;

    public ActivarUbicacionService(ObtenerUbicacionNoActivaService obtenerUbicacionNoActivaService) {
        this.obtenerUbicacionNoActivaService = obtenerUbicacionNoActivaService;
    }

    @Transactional
    public void activar(Long id) { obtenerUbicacionNoActivaService.obtener(id).activar(); }
}