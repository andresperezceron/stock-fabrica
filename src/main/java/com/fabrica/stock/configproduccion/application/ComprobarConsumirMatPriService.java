package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.application.ComprobarPaletAbiertoService;
import com.fabrica.stock.palet.application.ComprobarPaletTipoGranelService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class ComprobarConsumirMatPriService {
    private final ComprobarPaletTipoGranelService comprobarPaletTipoGranelService;
    private final ComprobarPaletAbiertoService comprobarPaletAbiertoService;

    public ComprobarConsumirMatPriService(ComprobarPaletTipoGranelService comprobarPaletTipoGranelService,
                                          ComprobarPaletAbiertoService comprobarPaletAbiertoService)
    {
        this.comprobarPaletTipoGranelService = comprobarPaletTipoGranelService;
        this.comprobarPaletAbiertoService = comprobarPaletAbiertoService;
    }

    public void comprobar(Maquina maquina, Palet palet, ConfigProduccion config) {

        if(!maquina.tengoProductoActivo())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(maquina.estaEnProduccion() && comprobarPaletTipoGranelService.comprobar(maquina))
            throw new RecursoNoValidoException("Servicio no disponible en palet tipo granel.");

        else if(maquina.estaEnProduccion() && comprobarPaletAbiertoService.comprobar(maquina))
            throw new EstadoNoValidoException( "Servicio no disponible con palet abierto.");

        if(!config.contieneMateriaPrima(palet))
            throw new RecursoNoEncontradoException("Materia prima no está en la lista.");
    }
}