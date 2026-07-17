package com.fabrica.stock.paletizador.application;

import com.fabrica.stock.palet.application.CambiarEstadoPaletService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import org.springframework.stereotype.Service;

@Service
public class AbrirPaletService {
    private final CambiarEstadoPaletService cambiarEstadoPaletService;
    private final ObtenerPaletService obtenerPaletService;

    public AbrirPaletService(CambiarEstadoPaletService cambiarEstadoPaletService,
                             ObtenerPaletService obtenerPaletService) {
        this.cambiarEstadoPaletService = cambiarEstadoPaletService;
        this.obtenerPaletService = obtenerPaletService;
    }

    public void abrirPalet(Long idPalet){
        cambiarEstadoPaletService.abrir(obtenerPaletService.obtener(idPalet));
    }
}
