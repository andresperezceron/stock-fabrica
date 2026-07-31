package com.fabrica.stock.palet.api;

import com.fabrica.stock.palet.application.IniciarProduccionCajaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/palets")
public class PaletController {
    private final IniciarProduccionCajaService iniciarProduccionCajaService;

    public PaletController(IniciarProduccionCajaService iniciarProduccionCajaService) {
        this.iniciarProduccionCajaService = iniciarProduccionCajaService;
    }

    @PostMapping
    public ResponseEntity<Void> crear(@PathVariable Long maquinaId) {
        iniciarProduccionCajaService.iniciar(maquinaId);
        return ResponseEntity.noContent().build();
    }
}