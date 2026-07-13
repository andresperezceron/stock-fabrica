package com.fabrica.stock.configurador.application;

import com.fabrica.stock.configurador.api.dto.FinalizarPaletRequest;
import com.fabrica.stock.movimiento.application.ReubicarPaletService;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.palet.application.FinalizarPaletService;
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
    private final FinalizarPaletService finalizarPaletService;
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final ReubicarPaletService reubicarPaletService;

    public ConfigFinalizarPaletService(ObtenerPaletService obtenerPaletService,
                                       FinalizarPaletService finalizarPaletService,
                                       ObtenerUbicacionService obtenerUbicacionService,
                                       ReubicarPaletService reubicarPaletService) {
        this.obtenerPaletService = obtenerPaletService;
        this.finalizarPaletService = finalizarPaletService;
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.reubicarPaletService = reubicarPaletService;
    }

    @Transactional
    public void finalizarPalet(FinalizarPaletRequest request) {
        Palet palet = obtenerPaletService.obtener(request.paletId);
        finalizarPaletService.finalizar(palet);
        Ubicacion destinoValidacion = obtenerUbicacionService.obtener(UbicacionSistema.VALIDACION.name());
        reubicarPaletService.reubicar(palet, TipoMovimiento.ENTRADA, destinoValidacion);
    }
}