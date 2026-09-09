package com.fabrica.stock.consumoinyeccion.application;

import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ConsumirPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final CrearMovimientoService crearMovimientoService;

    public ConsumirPaletService(ObtenerPaletService obtenerPaletService,
                                CrearMovimientoService crearMovimientoService)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.crearMovimientoService = crearMovimientoService;
    }

    @Transactional
    public void consumir(Long paletId) {
        Palet palet = obtenerPaletService.obtener(paletId);

        if(!palet.estoyEstacionConsumoInyeccion())
            throw new RecursoNoValidoException("Palet no está en Consumo Inyección.");

        crearMovimientoService.consumicion(palet);
    }
}