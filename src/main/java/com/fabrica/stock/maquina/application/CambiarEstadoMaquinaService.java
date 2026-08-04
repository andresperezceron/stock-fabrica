package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.maquina.domain.Maquina;
import org.springframework.stereotype.Service;

@Service
public class CambiarEstadoMaquinaService {
    private final ObtenerMaquinaService obtenerMaquinaService;

    public CambiarEstadoMaquinaService(ObtenerMaquinaService obtenerMaquinaService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    public void cambiar(Long id, EstadoMaquina estado) {
        Maquina maquina = obtenerMaquinaService.obtener(id);
        switch(estado) {
            case CAMBIO_MOLDE -> {
                maquina.cambiarACambioMolde();
                maquina.quitarProductoActivo();
            }
            case FUERA_SERVICIO -> {
                maquina.cambiarAFueraServicio();
                maquina.quitarProductoActivo();
            }
            case MANTENIMIENTO -> {
                maquina.cambiarAMantenimiento();
                maquina.quitarProductoActivo();
            }
            case APTA ->  maquina.cambiarAAptaProduccion();
        }
    }
}