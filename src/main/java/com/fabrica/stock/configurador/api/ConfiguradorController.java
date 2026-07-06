package com.fabrica.stock.configurador.api;

import com.fabrica.stock.configurador.api.dto.CrearPaletRequest;
import com.fabrica.stock.palet.application.CrearPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/configurador-palets")
public class ConfiguradorController {
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final CrearPaletService crearPaletService;

    public ConfiguradorController(ObtenerUbicacionService obtenerUbicacionService,
                                  CrearPaletService crearPaletService)
    {
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.crearPaletService = crearPaletService;
    }

    public Palet crear(CrearPaletRequest request) {
        return null;
    }
}
