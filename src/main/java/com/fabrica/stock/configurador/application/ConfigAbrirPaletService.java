package com.fabrica.stock.configurador.application;

import com.fabrica.stock.palet.application.CambiarEstadoPaletService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import org.springframework.stereotype.Service;

@Service
public class ConfigAbrirPaletService {
    private final CambiarEstadoPaletService cambiarEstadoPaletService;
    private final ObtenerPaletService obtenerPaletService;

    public ConfigAbrirPaletService(CambiarEstadoPaletService cambiarEstadoPaletService,
                                   ObtenerPaletService obtenerPaletService) {
        this.cambiarEstadoPaletService = cambiarEstadoPaletService;
        this.obtenerPaletService = obtenerPaletService;
    }

    public void abrirPalet(Long idPalet){
        cambiarEstadoPaletService.abrir(obtenerPaletService.obtener(idPalet));
    }
}
