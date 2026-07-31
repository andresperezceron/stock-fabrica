package com.fabrica.stock.palet.application;

import com.fabrica.stock.lote.application.CrearLoteSiNoExisteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.exceptions.EstadoMaquinaNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class RegistrarProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final CrearLoteSiNoExisteService crearLoteSiNoExisteService;

    public RegistrarProduccionService(ObtenerMaquinaService obtenerMaquinaService,
                                      CrearLoteSiNoExisteService crearLoteSiNoExisteService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.crearLoteSiNoExisteService = crearLoteSiNoExisteService;
    }

    public void registrar(Long maquinaId, Long paletId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        if(!maquina.estaEnProduccion()) {
            throw new EstadoMaquinaNoValidoException("No se puede registrar producción con la maquina en estado: " +
                    maquina.getEstado());
        }

        Lote lote = crearLoteSiNoExisteService.crearSiNoExiste(
                OrigenLote.PRODUCCION,
                null,
                maquina,
                maquina.getProductoActivo()
        );
    }
}