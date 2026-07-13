package com.fabrica.stock.configurador.application;

import com.fabrica.stock.movimiento.api.dto.ReubicarPaletRequest;
import com.fabrica.stock.movimiento.application.ReubicarPaletService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import org.springframework.stereotype.Service;

@Service
public class ConfigReubicarPaletService {
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final ObtenerPaletService obtenerPaletService;
    private final ReubicarPaletService reubicarPaletService;

    public ConfigReubicarPaletService(ObtenerUbicacionService obtenerUbicacionService,
                                      ObtenerPaletService obtenerPaletService,
                                      ReubicarPaletService reubicarPaletService) {
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.obtenerPaletService = obtenerPaletService;
        this.reubicarPaletService = reubicarPaletService;
    }

    public void reubicarPalet(ReubicarPaletRequest request) {
        reubicarPaletService.reubicar(
                obtenerPaletService.obtener(request.paletId),
                request.tipoMovimiento,
                obtenerUbicacionService.obtener(request.destinoId)
        );
    }
}
