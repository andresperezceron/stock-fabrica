package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarUbicacionService {
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final ValidarNombreUbicacionService validarNombreUbicacionService;

    public ActualizarUbicacionService(ObtenerUbicacionService obtenerUbicacionService,
                                      ValidarNombreUbicacionService validarNombreUbicacionService) {
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.validarNombreUbicacionService = validarNombreUbicacionService;
    }

    @Transactional
    public void actualizar(Long id, UbicacionRequest request) {
        Ubicacion ubicacion = obtenerUbicacionService.obtener(id);
        ubicacion.actualizarNombre(validarNombreUbicacionService.validar(request.nombre(), ubicacion));
    }
}