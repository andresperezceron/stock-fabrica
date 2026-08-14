package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.application.ComprobarPaletTipoGranelService;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class ComprobarActCajasPorPaletService {
    private final ComprobarPaletTipoGranelService comprobarPaletTipoGranelService;

    public ComprobarActCajasPorPaletService(ComprobarPaletTipoGranelService comprobarPaletTipoGranelService) {
        this.comprobarPaletTipoGranelService = comprobarPaletTipoGranelService;
    }

    public void comprobar(Maquina maquina, Integer cajasPorPalet) {

        if(!maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(comprobarPaletTipoGranelService.comprobar(maquina))
            throw new RecursoNoValidoException("Panet granel piezas por caja solo pueder 1.");

        if(cajasPorPalet == null || cajasPorPalet <= 0)
            throw new RecursoNoValidoException("Numero de cajas por palet no válido.");
    }
}
