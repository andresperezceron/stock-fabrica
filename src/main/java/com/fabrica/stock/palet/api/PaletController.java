package com.fabrica.stock.palet.api;

import com.fabrica.stock.palet.application.CrearPaletCajaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/palets")
public class PaletController {
    private final CrearPaletCajaService crearPaletCajaService;

    public PaletController(CrearPaletCajaService crearPaletCajaService) {
        this.crearPaletCajaService = crearPaletCajaService;
    }

    @PostMapping
    public ResponseEntity<Void> crear(@PathVariable Long maquinaId) {
        crearPaletCajaService.crear(maquinaId);
        return ResponseEntity.noContent().build();
    }
}