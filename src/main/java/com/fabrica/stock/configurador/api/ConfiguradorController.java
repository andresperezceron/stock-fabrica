package com.fabrica.stock.configurador.api;

import com.fabrica.stock.configurador.api.dto.CerrarPaletRequest;
import com.fabrica.stock.configurador.api.dto.FinalizarPaletRequest;
import com.fabrica.stock.configurador.api.dto.NuevoPaletRequest;
import com.fabrica.stock.configurador.api.dto.NuevoPaletResponse;
import com.fabrica.stock.configurador.application.ConfiguradorPaletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configurador-palets")
public class ConfiguradorController {
    private final ConfiguradorPaletService configuradorPaletService;

    public ConfiguradorController(ConfiguradorPaletService configuradorPaletService) {
        this.configuradorPaletService = configuradorPaletService;
    }

    @PostMapping
    public NuevoPaletResponse crear(@Valid @RequestBody NuevoPaletRequest request) {
        return configuradorPaletService.nuevoPalet(request);
    }

    @PostMapping("/{id}/abrir")
    public void abrir(@PathVariable Long id) {
        configuradorPaletService.abrirPalet(id);
    }

    @PostMapping("/cerrar")
    public void cerrar(@Valid @RequestBody CerrarPaletRequest request) {
        configuradorPaletService.cerrarPalet(request);
    }

    @PostMapping("/finalizar")
    public void finalizar(@Valid @RequestBody FinalizarPaletRequest request) {
        configuradorPaletService.finalizarPalet(request);
    }
}
