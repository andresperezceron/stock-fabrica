package com.fabrica.stock.palet.api;

import com.fabrica.stock.palet.api.dto.RegistrarProduccionRequest;
import com.fabrica.stock.palet.application.AbrirPaletService;
import com.fabrica.stock.palet.application.FinalizarPaletService;
import com.fabrica.stock.palet.application.RegistrarProduccionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/palets")
public class PaletController {
    private final RegistrarProduccionService registrarProduccionService;
    private final AbrirPaletService abrirPaletService;
    private final FinalizarPaletService finalizarPaletService;

    public PaletController(RegistrarProduccionService registrarProduccionService,
                           AbrirPaletService abrirPaletService,
                           FinalizarPaletService finalizarPaletService)
    {
        this.registrarProduccionService = registrarProduccionService;
        this.abrirPaletService = abrirPaletService;
        this.finalizarPaletService = finalizarPaletService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(@Valid @RequestBody RegistrarProduccionRequest request) {
        registrarProduccionService.registrar(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/abrir")
    public ResponseEntity<Void> abrir(@PathVariable Long id) {
        abrirPaletService.abrir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizar(@PathVariable Long id) {
        finalizarPaletService.finalizar(id);
        return ResponseEntity.noContent().build();
    }
}