package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.configproduccion.api.dto.ObsConfigProduccionRequest;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarObservacionesService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ObtenerMaquinaService obtenerMaquinaService;

    public ActualizarObservacionesService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                          ObtenerMaquinaService obtenerMaquinaService)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    @Transactional
    public void actualizar(Long maquinaId, ObsConfigProduccionRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);

        if(!maquina.tengoProductoActivo())
            throw new EstadoNoValidoException("Servicio no disponible.");

        String obsNormalizada = request.observaciones() == null ? "" : request.observaciones().trim();
        obtenerConfigProduccionService
                .porProducto(maquina.getProductoActivo())
                .actualizarObservaciones(obsNormalizada);
    }
}