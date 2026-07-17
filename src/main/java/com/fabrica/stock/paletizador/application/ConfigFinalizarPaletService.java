package com.fabrica.stock.paletizador.application;

import com.fabrica.stock.paletizador.api.dto.FinalizarPaletRequest;
import com.fabrica.stock.movimiento.application.ReubicarPaletService;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.palet.application.CambiarEstadoPaletService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.UbicacionSistema;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ConfigFinalizarPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final ReubicarPaletService reubicarPaletService;
    private final CambiarEstadoPaletService cambiarEstadoPaletService;

    public ConfigFinalizarPaletService(ObtenerPaletService obtenerPaletService,
                                       ObtenerUbicacionService obtenerUbicacionService,
                                       ReubicarPaletService reubicarPaletService,
                                       CambiarEstadoPaletService cambiarEstadoPaletService)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.reubicarPaletService = reubicarPaletService;
        this.cambiarEstadoPaletService = cambiarEstadoPaletService;
    }

    @Transactional
    public void finalizarPalet(FinalizarPaletRequest request) {
        Palet palet = obtenerPaletService.obtener(request.paletId);
        cambiarEstadoPaletService.finalizar(palet);
        Ubicacion destinoValidacion = obtenerUbicacionService.obtener(UbicacionSistema.VALIDACION.name());
        reubicarPaletService.reubicar(palet, TipoMovimiento.ENTRADA, destinoValidacion);
    }
}