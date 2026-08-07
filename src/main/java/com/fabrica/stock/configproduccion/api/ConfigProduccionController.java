package com.fabrica.stock.configproduccion.api;

import com.fabrica.stock.configproduccion.application.AgregarMateriaPrimaService;
import com.fabrica.stock.configproduccion.application.QuitarMateriaPrimaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config-produccion")
public class ConfigProduccionController {
    private final AgregarMateriaPrimaService agregarMateriaPrimaService;
    private final QuitarMateriaPrimaService quitarMateriaPrimaService;

    public ConfigProduccionController(AgregarMateriaPrimaService agregarMateriaPrimaService,
                                      QuitarMateriaPrimaService quitarMateriaPrimaService)
    {
        this.agregarMateriaPrimaService = agregarMateriaPrimaService;
        this.quitarMateriaPrimaService = quitarMateriaPrimaService;
    }

    @PutMapping("/{maquinaId}/materias-primas/{materiaPrimaId}")
    public ResponseEntity<Void> agregar(
            @PathVariable Long maquinaId,
            @PathVariable Long materiaPrimaId) {
        agregarMateriaPrimaService.agregar(maquinaId, materiaPrimaId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{maquinaId}/materias-primas/{materiaPrimaId}")
    public ResponseEntity<Void> quitar(
            @PathVariable Long maquinaId,
            @PathVariable Long materiaPrimaId) {
        quitarMateriaPrimaService.quitar(maquinaId, materiaPrimaId);
        return ResponseEntity.noContent().build();
    }

}