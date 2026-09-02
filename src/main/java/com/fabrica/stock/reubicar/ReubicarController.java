package com.fabrica.stock.reubicar;

import com.fabrica.stock.reubicar.application.ValidarPaletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reubicar")
public class ReubicarController {
    private final ValidarPaletService validarPaletService;

    public ReubicarController(ValidarPaletService validarPaletService) {
        this.validarPaletService = validarPaletService;
    }

    @PostMapping("/{paletId}/validar")
    public ResponseEntity<Void> validar(@PathVariable Long paletId, @RequestParam Long ubicacionId) {
        validarPaletService.validar(paletId, ubicacionId);
        return ResponseEntity.noContent().build();
    }
}