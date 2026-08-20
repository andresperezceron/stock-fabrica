package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.maquina.api.dto.request.CajasPorPaletRequest;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarCajasPorPaletService {
    private final ObtenerMaquinaService obtenerMaquinaService;

    public ActualizarCajasPorPaletService(ObtenerMaquinaService obtenerMaquinaService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    @Transactional
    public void actualizar(Long maquinaId, CajasPorPaletRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);

        if(!maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(maquina.getPalet().soyTipoGranel())
            throw new RecursoNoValidoException("Servicio no disponible.");

        maquina.getConfigProduccion().actualizarCajasPorPalet(request.cajasPorPalet());
    }
}