package com.fabrica.stock.ubicacion.api;

import com.fabrica.stock.ubicacion.api.dto.CrearUbicacionRequest;
import com.fabrica.stock.ubicacion.api.dto.ActualizarUbicacionRequest;
import com.fabrica.stock.ubicacion.api.dto.UbicacionesResponse;
import com.fabrica.stock.ubicacion.api.dto.UbicacionResponse;
import com.fabrica.stock.ubicacion.application.*;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {
    private final CrearUbicacionService crearUbicacionService;
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final ActualizarUbicacionService actualizarUbicacionService;
    private final ListarUbicacionesService listarUbicacionesService;
    private final ListarUbicacionesNoActivasService listarUbicacionesNoActivasService;
    private final EliminarUbicacionService eliminarUbicacionService;
    private final ActivarUbicacionService activarUbicacionService;

    public UbicacionController(
            CrearUbicacionService crearUbicacionService,
            ObtenerUbicacionService obtenerUbicacionService,
            ActualizarUbicacionService actualizarUbicacionService,
            ListarUbicacionesService listarUbicacionesService,
            ListarUbicacionesNoActivasService listarUbicacionesNoActivasService,
            EliminarUbicacionService eliminarUbicacionService,
            ActivarUbicacionService activarUbicacionService)
    {
        this.crearUbicacionService = crearUbicacionService;
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.actualizarUbicacionService = actualizarUbicacionService;
        this.listarUbicacionesService = listarUbicacionesService;
        this.listarUbicacionesNoActivasService = listarUbicacionesNoActivasService;
        this.eliminarUbicacionService = eliminarUbicacionService;
        this.activarUbicacionService = activarUbicacionService;
    }

    @GetMapping
    public ResponseEntity<UbicacionesResponse> listar() {
        List<Ubicacion> ubicaciones = listarUbicacionesService.listar();
        return ResponseEntity.ok(UbicacionesResponse.of(ubicaciones));
    }

    @GetMapping("/inactivas")
    public ResponseEntity<UbicacionesResponse> listarInactivas() {
        List<Ubicacion> inactivas = listarUbicacionesNoActivasService.listar();
        return ResponseEntity.ok(UbicacionesResponse.of(inactivas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionResponse> obtener(@PathVariable Long id) {
        Ubicacion ubicacion = obtenerUbicacionService.obtener(id);
        return ResponseEntity.ok(UbicacionResponse.of(ubicacion));
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody CrearUbicacionRequest request) {
        crearUbicacionService.crear(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarUbicacionRequest request){
        actualizarUbicacionService.actualizar(id, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<Void> activar(@PathVariable Long id) {
        activarUbicacionService.activar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        eliminarUbicacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}