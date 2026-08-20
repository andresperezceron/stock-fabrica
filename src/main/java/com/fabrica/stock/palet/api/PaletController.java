package com.fabrica.stock.palet.api;

import com.fabrica.stock.palet.api.dto.response.VerPaletDetalleResponse;
import com.fabrica.stock.palet.api.dto.response.VerPaletResponse;
import com.fabrica.stock.palet.api.dto.response.PaletsConsumoInyeccionResponse;
import com.fabrica.stock.palet.application.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/palets")
public class PaletController {
    private final VerPaletService verPaletService;
    private final VerPaletDetalleService verPaletDetalleService;
    private final PaletsConsumoInyeccionService paletsConsumoInyeccionService;

    public PaletController(VerPaletService verPaletService,
                           VerPaletDetalleService verPaletDetalleService,
                           PaletsConsumoInyeccionService paletsConsumoInyeccionService)
    {
        this.verPaletService = verPaletService;
        this.verPaletDetalleService = verPaletDetalleService;
        this.paletsConsumoInyeccionService = paletsConsumoInyeccionService;
    }

    @GetMapping("/consumo-inyeccion")
    public ResponseEntity<PaletsConsumoInyeccionResponse> listarPaletsConsumoInyeccion() {
        return ResponseEntity.ok(paletsConsumoInyeccionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VerPaletResponse> verPalet(@PathVariable Long id) {
        return ResponseEntity.ok(verPaletService.ver(id));
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<VerPaletDetalleResponse> verPaletDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(verPaletDetalleService.ver(id));
    }
}