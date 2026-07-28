package com.fabrica.stock.palet.api;

import com.fabrica.stock.palet.api.dto.PaletRequest;
import com.fabrica.stock.palet.application.CrearPaletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/palets")
public class PaletController {
    private final CrearPaletService crearPaletService;

    public PaletController(CrearPaletService crearPaletService) {
        this.crearPaletService = crearPaletService;
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody PaletRequest request) {
        crearPaletService.crear(request);
        return ResponseEntity.noContent().build();
    }
}