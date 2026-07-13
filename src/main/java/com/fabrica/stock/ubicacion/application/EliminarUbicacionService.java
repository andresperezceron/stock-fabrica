package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EliminarUbicacionService {
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final ComprobarUsoUbicacionService comprobarUsoUbicacionService;
    private final UbicacionRepository ubicacionRepository;

    public EliminarUbicacionService(ObtenerUbicacionService obtenerUbicacionService,
                                    ComprobarUsoUbicacionService comprobarUsoUbicacionService,
                                    UbicacionRepository ubicacionRepository)
    {
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.comprobarUsoUbicacionService = comprobarUsoUbicacionService;
        this.ubicacionRepository = ubicacionRepository;
    }

    @Transactional
    public void eliminar(Long ubicacionId) {
        Ubicacion ubicacion = obtenerUbicacionService.obtener(ubicacionId);

        if(comprobarUsoUbicacionService.tieneUso(ubicacion)) {
            ubicacion.desactivar();
        } else ubicacionRepository.delete(ubicacion);
    }
}