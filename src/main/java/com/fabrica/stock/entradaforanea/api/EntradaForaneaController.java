package com.fabrica.stock.entradaforanea.api;

import com.fabrica.stock.entradaforanea.api.dto.AgregarContenidoRequest;
import com.fabrica.stock.entradaforanea.api.dto.CrearPaletGranelRequest;
import com.fabrica.stock.entradaforanea.api.dto.PaletCajasVacioResponse;
import com.fabrica.stock.entradaforanea.application.AgregarContenidoPaletService;
import com.fabrica.stock.entradaforanea.application.CrearPaletCajasVacioService;
import com.fabrica.stock.entradaforanea.application.CrearPaletGranelService;
import com.fabrica.stock.entradaforanea.application.FinalizarPaletCajasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entrada-foranea")
public class EntradaForaneaController {
    private final CrearPaletGranelService crearPaletGranelService;
    private final CrearPaletCajasVacioService crearPaletCajasVacioService;
    private final AgregarContenidoPaletService agregarContenidoPaletService;
    private final FinalizarPaletCajasService finalizarPaletCajasService;

    public EntradaForaneaController(CrearPaletGranelService crearPaletGranelService,
                                    CrearPaletCajasVacioService crearPaletCajasVacioService,
                                    AgregarContenidoPaletService agregarContenidoPaletService,
                                    FinalizarPaletCajasService finalizarPaletCajasService)
    {
        this.crearPaletGranelService = crearPaletGranelService;
        this.crearPaletCajasVacioService = crearPaletCajasVacioService;
        this.agregarContenidoPaletService = agregarContenidoPaletService;
        this.finalizarPaletCajasService = finalizarPaletCajasService;
    }

    @PostMapping
    public ResponseEntity<Void> crearPaletGranel(@Valid @RequestBody CrearPaletGranelRequest request) {
        crearPaletGranelService.crear(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cajas/{productoId}")
    public ResponseEntity<PaletCajasVacioResponse> crearPaletCajasVacio(@PathVariable Long productoId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crearPaletCajasVacioService.crear(productoId));
    }

    @PostMapping("/cajas/agregar-contenido")
    public ResponseEntity<Void> agregarContenidoPaletCajas(@Valid @RequestBody AgregarContenidoRequest request) {
        agregarContenidoPaletService.agregar(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cajas/finalizar/{paletId}")
    public ResponseEntity<Void> finalizarPaletCajas(@PathVariable Long paletId) {
        finalizarPaletCajasService.finalizar(paletId);
        return ResponseEntity.noContent().build();
    }
}