package com.fabrica.stock.paletizador.application;

import com.fabrica.stock.palet.application.CambiarEstadoPaletService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import org.springframework.stereotype.Service;

@Service
public class ConfigValidarPaletService {
    private final CambiarEstadoPaletService cambiarEstadoPaletService;
    private final ObtenerPaletService obtenerPaletService;

    public ConfigValidarPaletService(CambiarEstadoPaletService cambiarEstadoPaletService,
                                     ObtenerPaletService obtenerPaletService) {
        this.cambiarEstadoPaletService = cambiarEstadoPaletService;
        this.obtenerPaletService = obtenerPaletService;
    }

    public void validar(Long id) {
        Palet palet = obtenerPaletService.obtener(id);
        cambiarEstadoPaletService.validar(palet);
    }
}