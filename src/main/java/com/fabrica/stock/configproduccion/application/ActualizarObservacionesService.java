package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.maquina.api.dto.request.ActualizarObsRequest;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarObservacionesService {
    private final ObtenerMaquinaService obtenerMaquinaService;

    public ActualizarObservacionesService(ObtenerMaquinaService obtenerMaquinaService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    @Transactional
    public void actualizar(Long maquinaId, ActualizarObsRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);

        if(maquina.noTengoConfigProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        String obsNormalizada = request.observaciones() == null ? "" : request.observaciones().trim();
        maquina.getConfigProduccion().actualizarObservaciones(obsNormalizada);
    }
}