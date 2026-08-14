package com.fabrica.stock.palet.api;

import com.fabrica.stock.palet.api.dto.MaquinaIdPaletIdRequest;
import com.fabrica.stock.palet.api.dto.RegistrarProduccionRequest;
import com.fabrica.stock.palet.application.AbrirPaletService;
import com.fabrica.stock.palet.application.FinalizarPaletService;
import com.fabrica.stock.palet.application.RegistrarCajaPersonalizadaService;
import com.fabrica.stock.palet.application.RegistrarProduccionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/palets")
public class PaletController {
    private final RegistrarProduccionService registrarProduccionService;
    private final RegistrarCajaPersonalizadaService registrarCajaPersonalizadaService;
    private final AbrirPaletService abrirPaletService;
    private final FinalizarPaletService finalizarPaletService;

    public PaletController(RegistrarProduccionService registrarProduccionService,
                           RegistrarCajaPersonalizadaService registrarCajaPersonalizadaService,
                           AbrirPaletService abrirPaletService,
                           FinalizarPaletService finalizarPaletService)
    {
        this.registrarProduccionService = registrarProduccionService;
        this.registrarCajaPersonalizadaService = registrarCajaPersonalizadaService;
        this.abrirPaletService = abrirPaletService;
        this.finalizarPaletService = finalizarPaletService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(@Valid @RequestBody RegistrarProduccionRequest request) {
        registrarProduccionService.registrar(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/caja-personalizada")
    public ResponseEntity<Void> cajaPersonalizada(@Valid @RequestBody RegistrarProduccionRequest request) {
        registrarCajaPersonalizadaService.registrar(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/abrir")
    public ResponseEntity<Void> abrir(@Valid @RequestBody MaquinaIdPaletIdRequest request) {
        abrirPaletService.abrir(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/finalizar")
    public ResponseEntity<Void> finalizar(@Valid @RequestBody MaquinaIdPaletIdRequest request) {
        finalizarPaletService.finalizar(request);
        return ResponseEntity.noContent().build();
    }
}