package com.fabrica.stock.produccion.api;

import com.fabrica.stock.produccion.application.CerrarPaletService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produccion")
public class ProduccionController {
    private final CerrarPaletService cerrarPaletService;

    public ProduccionController(CerrarPaletService cerrarPaletService) {
        this.cerrarPaletService = cerrarPaletService;
    }
}
