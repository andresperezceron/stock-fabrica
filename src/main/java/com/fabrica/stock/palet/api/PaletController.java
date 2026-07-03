package com.fabrica.stock.palet.api;

import com.fabrica.stock.palet.api.dto.AbrirPaletRequest;
import com.fabrica.stock.palet.api.dto.AbrirPaletResponse;
import com.fabrica.stock.palet.application.AbrirPaletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/palets")
public class PaletController {
    private final AbrirPaletService abrirPaletService;

    public PaletController(AbrirPaletService abrirPaletService) {
        this.abrirPaletService = abrirPaletService;
    }

    @PostMapping
    public AbrirPaletResponse abrirPalet(@Valid @RequestBody AbrirPaletRequest request) {
        return abrirPaletService.abrir(request);
    }
}