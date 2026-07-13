package com.fabrica.stock.ubicacion.api;

import com.fabrica.stock.ubicacion.api.dto.CrearUbicacionRequest;
import com.fabrica.stock.ubicacion.api.dto.ModificarUbicacionRequest;
import com.fabrica.stock.ubicacion.application.ActualizarUbicacionService;
import com.fabrica.stock.ubicacion.application.CrearUbicacionService;
import com.fabrica.stock.ubicacion.application.ListarUbicacionService;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
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
    private final ListarUbicacionService listarUbicacionesService;

    public UbicacionController(
            CrearUbicacionService crearUbicacionService,
            ObtenerUbicacionService obtenerUbicacionService,
            ActualizarUbicacionService actualizarUbicacionService,
            ListarUbicacionService listarUbicacionesService)
    {
        this.crearUbicacionService = crearUbicacionService;
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.actualizarUbicacionService = actualizarUbicacionService;
        this.listarUbicacionesService = listarUbicacionesService;
    }

    @GetMapping
    public List<Ubicacion> listar() {
        return listarUbicacionesService.listar();
    }

    @PostMapping
    public Ubicacion crear(@Valid @RequestBody CrearUbicacionRequest request) {
        return crearUbicacionService.crear(request);
    }

    @PostMapping("/{id}")
    public Ubicacion obtener(@PathVariable Long id){
        return obtenerUbicacionService.obtener(id);
    }

    @PutMapping("/{id}")
    public Ubicacion actualizar(@Valid @RequestBody ModificarUbicacionRequest request){
        return actualizarUbicacionService.actualizar(request);
    }
}