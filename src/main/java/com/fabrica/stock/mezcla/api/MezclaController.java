package com.fabrica.stock.mezcla.api;

import com.fabrica.stock.mezcla.api.dto.*;
import com.fabrica.stock.mezcla.application.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mezclas")
public class MezclaController {
    private final CrearMezclaService crearMezclaService;
    private final ObtenerMezclaService obtenerMezclaService;
    private final AgregarIngredienteService agregarIngredienteService;
    private final QuitarIngredienteService quitarIngredienteService;
    private final ListarMezclasService listarMezclasService;

    public MezclaController(CrearMezclaService crearMezclaService,
                            ObtenerMezclaService obtenerMezclaService,
                            AgregarIngredienteService agregarIngredienteService,
                            QuitarIngredienteService quitarIngredienteService,
                            ListarMezclasService listarMezclasService)
    {
        this.crearMezclaService = crearMezclaService;
        this.obtenerMezclaService = obtenerMezclaService;
        this.agregarIngredienteService = agregarIngredienteService;
        this.quitarIngredienteService = quitarIngredienteService;
        this.listarMezclasService = listarMezclasService;
    }

    @GetMapping
    public ResponseEntity<MezclasResponse> listar() {
        return ResponseEntity.ok(this.listarMezclasService.listar());
    }

    @GetMapping("/{mezclaId}")
    public ResponseEntity<MezclaDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(MezclaDto.of(obtenerMezclaService.obtener(id)));
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody CrearMezclaRequest request) {
        crearMezclaService.crear(request.productoId(), request.paletsId());
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