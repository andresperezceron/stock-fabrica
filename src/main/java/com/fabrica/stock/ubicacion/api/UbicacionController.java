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
    private final ListarUbicacionActivaService listarUbicacionesService;
    private final EliminarUbicacionService eliminarUbicacionService;

    public UbicacionController(
            CrearUbicacionService crearUbicacionService,
            ObtenerUbicacionService obtenerUbicacionService,
            ActualizarUbicacionService actualizarUbicacionService,
            ListarUbicacionActivaService listarUbicacionesService,
            EliminarUbicacionService eliminarUbicacionService)
    {
        this.crearUbicacionService = crearUbicacionService;
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.actualizarUbicacionService = actualizarUbicacionService;
        this.listarUbicacionesService = listarUbicacionesService;
        this.eliminarUbicacionService = eliminarUbicacionService;
    }

    @GetMapping
    public List<Ubicacion> listar() {
        return listarUbicacionesService.listar();
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

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        eliminarUbicacionService.eliminar(id);
    }
}