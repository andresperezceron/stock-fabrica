package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.maquina.domain.Maquina;

public class AsignarEstadoService {
    private final ObtenerMaquinaService obtenerMaquinaService;

    public AsignarEstadoService(ObtenerMaquinaService obtenerMaquinaService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    public void asignar(Long id, EstadoMaquina estado) {
        Maquina maquina = obtenerMaquinaService.obtener(id);
        switch(estado) {
            case CAMBIO_MOLDE -> {
                maquina.asignarEstadoCambioMolde();
                maquina.quitarProductoActivo();
            }
            case FUERA_SERVICIO -> {
                maquina.asignarEstadoFueraServicio();
                maquina.quitarProductoActivo();
            }
            case APTA_PRODUCCION ->  maquina.asignarEstadoAptaProduccion();
        }
    }
}