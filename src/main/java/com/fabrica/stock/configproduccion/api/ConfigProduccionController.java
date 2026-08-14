package com.fabrica.stock.configproduccion.api;

import com.fabrica.stock.configproduccion.api.dto.ObsConfigProduccionRequest;
import com.fabrica.stock.configproduccion.application.ActualizarCajasPorPaletService;
import com.fabrica.stock.configproduccion.application.ActualizarObservacionesService;
import com.fabrica.stock.configproduccion.application.AgregarMateriaPrimaService;
import com.fabrica.stock.configproduccion.application.ConsumirMateriaPrimaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config-produccion")
public class ConfigProduccionController {
    private final AgregarMateriaPrimaService agregarMateriaPrimaService;
    private final ConsumirMateriaPrimaService consumirMateriaPrimaService;
    private final ActualizarObservacionesService actualizarObservacionesService;
    private final ActualizarCajasPorPaletService actualizarCajasPorPaletService;

    public ConfigProduccionController(AgregarMateriaPrimaService agregarMateriaPrimaService,
                                      ConsumirMateriaPrimaService consumirMateriaPrimaService,
                                      ActualizarObservacionesService actualizarObservacionesService,
                                      ActualizarCajasPorPaletService actualizarCajasPorPaletService)
    {
        this.agregarMateriaPrimaService = agregarMateriaPrimaService;
        this.consumirMateriaPrimaService = consumirMateriaPrimaService;
        this.actualizarObservacionesService = actualizarObservacionesService;
        this.actualizarCajasPorPaletService = actualizarCajasPorPaletService;
    }

    @PutMapping("/{maquinaId}/materias-primas/{paletId}")
    public ResponseEntity<Void> agregar(
            @PathVariable Long maquinaId,
            @PathVariable Long paletId) {
        agregarMateriaPrimaService.agregar(maquinaId, paletId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{maquinaId}/observaciones")
    public ResponseEntity<Void> actualizarObservaciones(
            @PathVariable Long maquinaId,
            @Valid @RequestBody ObsConfigProduccionRequest request) {
        actualizarObservacionesService.actualizar(maquinaId, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{maquinaId}/cajas-por-palet/{cajasPorPalet}")
    public ResponseEntity<Void> actualizarCajasPorPalet(
            @PathVariable Long maquinaId,
            @PathVariable Integer cajasPorPalet) {
        actualizarCajasPorPaletService.actualizar(maquinaId, cajasPorPalet);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{maquinaId}/materias-primas/{paletId}")
    public ResponseEntity<Void> consumir(
            @PathVariable Long maquinaId,
            @PathVariable Long paletId) {
        consumirMateriaPrimaService.consumir(maquinaId, paletId);
        return ResponseEntity.noContent().build();
    }
}