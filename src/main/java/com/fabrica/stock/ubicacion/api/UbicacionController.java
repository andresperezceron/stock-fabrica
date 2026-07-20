package com.fabrica.stock.ubicacion.api;

import com.fabrica.stock.ubicacion.api.dto.CrearUbicacionRequest;
import com.fabrica.stock.ubicacion.api.dto.ActualizarUbicacionRequest;
import com.fabrica.stock.ubicacion.application.*;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {
    private final CrearUbicacionService crearUbicacionService;
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final ActualizarUbicacionService actualizarUbicacionService;
    private final ListarUbicacionesService listarUbicacionesService;
    private final ListarUbicacionesInactivasService listarUbicacionesInactivasService;
    private final EliminarUbicacionService eliminarUbicacionService;
    private final ActivarUbicacionService activarUbicacionService;

    public UbicacionController(
            CrearUbicacionService crearUbicacionService,
            ObtenerUbicacionService obtenerUbicacionService,
            ActualizarUbicacionService actualizarUbicacionService,
            ListarUbicacionesService listarUbicacionesService,
            ListarUbicacionesInactivasService listarUbicacionesInactivasService,
            EliminarUbicacionService eliminarUbicacionService,
            ActivarUbicacionService activarUbicacionService)
    {
        this.crearUbicacionService = crearUbicacionService;
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.actualizarUbicacionService = actualizarUbicacionService;
        this.listarUbicacionesService = listarUbicacionesService;
        this.listarUbicacionesInactivasService = listarUbicacionesInactivasService;
        this.eliminarUbicacionService = eliminarUbicacionService;
        this.activarUbicacionService = activarUbicacionService;
    }

    @GetMapping
    public List<Ubicacion> listar() {
        return listarUbicacionesService.listar();
    }

    @GetMapping("/inactivas")
    public List<Ubicacion> listarInactivas() {
        return listarUbicacionesInactivasService.listar();
    }

    @GetMapping("/{id}")
    public Ubicacion obtener(@PathVariable Long id){
        return obtenerUbicacionService.obtener(id);
    }

    @PostMapping
    public void crear(@Valid @RequestBody CrearUbicacionRequest request) {
        crearUbicacionService.crear(request);
    }

    @PutMapping
    public void actualizar(@Valid @RequestBody ActualizarUbicacionRequest request){
        actualizarUbicacionService.actualizar(request);
    }

    @PatchMapping("/{id}/activar")
    public void activar(@PathVariable Long id) {
        activarUbicacionService.activar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        eliminarUbicacionService.eliminar(id);
    }
}