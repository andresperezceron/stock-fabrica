package com.fabrica.stock.consumoinyeccion.api;

import com.fabrica.stock.consumoinyeccion.api.dto.CantidadModificada;
import com.fabrica.stock.consumoinyeccion.application.AgregarPaletService;
import com.fabrica.stock.consumoinyeccion.application.ConsumirPaletService;
import com.fabrica.stock.consumoinyeccion.application.DevolverPaletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumo-inyeccion")
public class ConsumoInyeccionController {
    private final AgregarPaletService agregarPaletService;
    private final ConsumirPaletService consumirPaletService;
    private final DevolverPaletService devolverPaletService;

    public ConsumoInyeccionController(AgregarPaletService agregarPaletService,
                                      ConsumirPaletService consumirPaletService,
                                      DevolverPaletService devolverPaletService)
    {
        this.agregarPaletService = agregarPaletService;
        this.consumirPaletService = consumirPaletService;
        this.devolverPaletService = devolverPaletService;
    }

    @PostMapping("/{paletId}")
    public ResponseEntity<Void> agregarPalet(@PathVariable Long paletId) {
        agregarPaletService.agregarPalet(paletId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{paletId}")
    public ResponseEntity<Void> consumirPalet(@PathVariable Long paletId) {
        consumirPaletService.consumir(paletId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{paletId}")
    public ResponseEntity<Void> devolverPalet(
            @PathVariable Long paletId,
            @Valid @RequestBody CantidadModificada request) {
        devolverPaletService.devolver(paletId,  request);
        return ResponseEntity.noContent().build();
    }
}