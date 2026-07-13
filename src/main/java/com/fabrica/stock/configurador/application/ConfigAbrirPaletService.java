package com.fabrica.stock.configurador.application;

import com.fabrica.stock.palet.application.AbrirPaletService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import org.springframework.stereotype.Service;

@Service
public class ConfigAbrirPaletService {
    private final AbrirPaletService abrirPaletService;
    private final ObtenerPaletService obtenerPaletService;

    public ConfigAbrirPaletService(AbrirPaletService abrirPaletService,
                                   ObtenerPaletService obtenerPaletService) {
        this.abrirPaletService = abrirPaletService;
        this.obtenerPaletService = obtenerPaletService;
    }

    public void abrirPalet(Long idPalet){
        abrirPaletService.abrir(obtenerPaletService.obtener(idPalet));
    }
}
