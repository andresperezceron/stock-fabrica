package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.api.dto.request.EstadoMaquinaRequest;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CambiarEstadoMaquinaService {
    private final ObtenerMaquinaService obtenerMaquinaService;

    public CambiarEstadoMaquinaService(ObtenerMaquinaService obtenerMaquinaService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    @Transactional
    public void cambiar(Long maquinaId, EstadoMaquinaRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        if(maquina.estaEnProduccion()) throw new EstadoNoValidoException("Servicio no disponible.");

        switch(request.estado()) {
            case CAMBIO_MOLDE -> {
                maquina.cambioMolde();
                maquina.quitarConfigProduccion();
            }
            case FUERA_SERVICIO -> {
                maquina.fueraServicio();
                maquina.quitarConfigProduccion();
            }
            case MANTENIMIENTO -> {
                maquina.mantenimiento();
                maquina.quitarConfigProduccion();
            }
            case APTA_PRODUCCION ->  maquina.aptaProduccion();

            default -> throw new RecursoNoValidoException("Estado máquina no valido.");
        }
    }
}