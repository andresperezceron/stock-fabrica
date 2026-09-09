package com.fabrica.stock.consumoinyeccion.application;

import com.fabrica.stock.consumoinyeccion.api.dto.CantidadModificada;
import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DevolverPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final CrearMovimientoService crearMovimientoService;

    public DevolverPaletService(ObtenerPaletService obtenerPaletService,
                                CrearMovimientoService crearMovimientoService)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.crearMovimientoService = crearMovimientoService;
    }

    @Transactional
    public void devolver(Long paletId, CantidadModificada request) {
        Palet palet = obtenerPaletService.obtener(paletId);
        Integer cantidad = request.cantidadModificada();

        if(!palet.estoyEstacionConsumoInyeccion())
            throw new RecursoNoValidoException("Palet no está en Consumo Inyección");

        palet.modificarContenidoPalet(cantidad);
        crearMovimientoService.devolucionConsumoInyeccion(palet);
    }
}