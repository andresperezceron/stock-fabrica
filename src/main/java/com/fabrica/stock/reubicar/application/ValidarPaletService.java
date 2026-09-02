package com.fabrica.stock.reubicar.application;

import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.stereotype.Service;

@Service
public class ValidarPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final CrearMovimientoService crearMovimientoService;

    public ValidarPaletService(ObtenerPaletService obtenerPaletService,
                               ObtenerUbicacionService obtenerUbicacionService,
                               CrearMovimientoService crearMovimientoService)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.crearMovimientoService = crearMovimientoService;
    }

    public void validar(Long paletId, Long ubicacionId) {
        Palet palet = obtenerPaletService.obtener(paletId);
        Ubicacion ubicacion = obtenerUbicacionService.obtener(ubicacionId);
        crearMovimientoService.validacion(palet, ubicacion);
    }
}
