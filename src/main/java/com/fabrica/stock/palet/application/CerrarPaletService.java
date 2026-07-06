package com.fabrica.stock.palet.application;

import com.fabrica.stock.produccion.api.dto.CerrarPaletRequest;
import org.springframework.stereotype.Service;

@Service
public class CerrarPaletService {
    private final ObtenerPaletService obtenerPaletService;

    public CerrarPaletService(ObtenerPaletService obtenerPaletService) {
        this.obtenerPaletService = obtenerPaletService;
    }

    public void cerrar(CerrarPaletRequest request) {

    }
}
