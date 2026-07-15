package com.fabrica.stock.configurador.api;

import com.fabrica.stock.configurador.api.dto.CerrarPaletRequest;
import com.fabrica.stock.configurador.api.dto.FinalizarPaletRequest;
import com.fabrica.stock.configurador.api.dto.NuevoPaletRequest;
import com.fabrica.stock.configurador.api.dto.NuevoPaletResponse;
import com.fabrica.stock.configurador.application.*;
import com.fabrica.stock.movimiento.api.dto.ReubicarPaletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configurador-palets")
public class ConfiguradorController {
    private final ConfigNuevoPaletService configNuevoPaletService;
    private final ConfigAbrirPaletService configAbrirPaletService;
    private final ConfigCerrarPaletService configCerrarPaletService;
    private final ConfigFinalizarPaletService configFinalizarPaletService;
    private final ConfigReubicarPaletService configReubicarPaletService;
    private final ConfigValidarPaletService configValidarPaletService;

    public ConfiguradorController(ConfigNuevoPaletService configNuevoPaletService,
                                  ConfigAbrirPaletService configAbrirPaletService,
                                  ConfigCerrarPaletService configCerrarPaletService,
                                  ConfigFinalizarPaletService configFinalizarPaletService,
                                  ConfigReubicarPaletService configReubicarPaletService,
                                  ConfigValidarPaletService configValidarPaletService)
    {
        this.configNuevoPaletService = configNuevoPaletService;
        this.configAbrirPaletService = configAbrirPaletService;
        this.configCerrarPaletService = configCerrarPaletService;
        this.configFinalizarPaletService = configFinalizarPaletService;
        this.configReubicarPaletService = configReubicarPaletService;
        this.configValidarPaletService = configValidarPaletService;
    }

    @PostMapping
    public NuevoPaletResponse crear(@Valid @RequestBody NuevoPaletRequest request) {
        return configNuevoPaletService.crearPalet(request);
    }

    @PostMapping("/{id}/abrir")
    public void abrir(@PathVariable Long id) {
        configAbrirPaletService.abrirPalet(id);
    }

    @PostMapping("/cerrar")
    public void cerrar(@Valid @RequestBody CerrarPaletRequest request) {
        configCerrarPaletService.cerrarPalet(request);
    }

    @PostMapping("/finalizar")
    public void finalizar(@Valid @RequestBody FinalizarPaletRequest request) {
        configFinalizarPaletService.finalizarPalet(request);
    }

    @PostMapping("/validar/{id}")
    public void validar(@PathVariable Long id) {
        configValidarPaletService.validar(id);
    }

    @PostMapping("/reubicar")
    public void reubicar(@Valid @RequestBody ReubicarPaletRequest request) {
        configReubicarPaletService.reubicarPalet(request);
    }
}