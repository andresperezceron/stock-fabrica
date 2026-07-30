package com.fabrica.stock.palet.application;

import com.fabrica.stock.lote.application.CrearLoteSiNoExisteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class RegistrarProduccionService {
    private final ObtenerPaletService obtenerPaletService;
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final CrearLoteSiNoExisteService crearLoteSiNoExisteService;

    public RegistrarProduccionService(ObtenerPaletService obtenerPaletService,
                                      ObtenerMaquinaService obtenerMaquinaService,
                                      CrearLoteSiNoExisteService crearLoteSiNoExisteService)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.crearLoteSiNoExisteService = crearLoteSiNoExisteService;
    }

    public void Registrar(Long maquinaId,  Long paletId) {
        Palet palet = obtenerPaletService.obtener(paletId);
        if(!palet.estaAbierto()) throw new EstadoPaletInvalidoException("El palet no está abierto.");
        Lote lote;
    }
}