package com.fabrica.stock.mezcla.api;

import com.fabrica.stock.mezcla.api.dto.IdLoteIngrediente;
import com.fabrica.stock.mezcla.api.dto.IdPaletIngrediente;
import com.fabrica.stock.mezcla.api.dto.MezclasResponse;
import com.fabrica.stock.mezcla.application.AgregarIngredienteService;
import com.fabrica.stock.mezcla.application.CrearMezclaService;
import com.fabrica.stock.mezcla.application.ListarMezclasService;
import com.fabrica.stock.mezcla.application.QuitarIngredienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mezclas")
public class MezclaController {
    private final CrearMezclaService crearMezclaService;
    private final AgregarIngredienteService agregarIngredienteService;
    private final QuitarIngredienteService quitarIngredienteService;
    private final ListarMezclasService listarMezclasService;

    public MezclaController(CrearMezclaService crearMezclaService,
                            AgregarIngredienteService agregarIngredienteService,
                            QuitarIngredienteService quitarIngredienteService,
                            ListarMezclasService listarMezclasService)
    {
        this.crearMezclaService = crearMezclaService;
        this.agregarIngredienteService = agregarIngredienteService;
        this.quitarIngredienteService = quitarIngredienteService;
        this.listarMezclasService = listarMezclasService;
    }

    @GetMapping
    public ResponseEntity<MezclasResponse> listar() {
        return ResponseEntity.ok(this.listarMezclasService.listar());
    }

    @PostMapping("/{productoId}")
    public ResponseEntity<Void> crear(@PathVariable Long productoId) {
        crearMezclaService.crear(productoId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{mezclaId}/ingrediente")
    public ResponseEntity<Void> agregarIngrediente(
            @PathVariable Long mezclaId,
            @Valid @RequestBody IdPaletIngrediente request) {
        agregarIngredienteService.agregar(mezclaId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{mezclaId}/ingrediente")
    public ResponseEntity<Void> quitarIngrediente(
            @PathVariable Long mezclaId,
            @Valid @RequestBody IdLoteIngrediente request) {
        quitarIngredienteService.quitar(mezclaId, request);
        return ResponseEntity.noContent().build();
    }
}