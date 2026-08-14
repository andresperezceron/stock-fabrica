package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class CambiarEstadoMaquinaService {
    private final ObtenerMaquinaService obtenerMaquinaService;

    public CambiarEstadoMaquinaService(ObtenerMaquinaService obtenerMaquinaService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    public void cambiar(Long id, EstadoMaquina estado) {
        Maquina maquina = obtenerMaquinaService.obtener(id);
        if(maquina.estaEnProduccion()) throw new EstadoNoValidoException("Máquina está en producción.");
        switch(estado) {
            case CAMBIO_MOLDE -> {
                maquina.cambioMolde();
                maquina.quitarProductoActivo();
            }
            case FUERA_SERVICIO -> {
                maquina.fueraServicio();
                maquina.quitarProductoActivo();
            }
            case MANTENIMIENTO -> {
                maquina.mantenimiento();
                maquina.quitarProductoActivo();
            }
            case APTA_PRODUCCION ->  maquina.aptaProduccion();
        }
    }
}