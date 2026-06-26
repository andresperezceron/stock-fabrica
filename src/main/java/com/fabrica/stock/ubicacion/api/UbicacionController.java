package com.fabrica.stock.ubicacion.api;

import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.application.CrearUbicacionService;
import com.fabrica.stock.ubicacion.application.ListarUbicacionesService;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {
    private final CrearUbicacionService crearUbicacionService;
    private final ListarUbicacionesService listarUbicacionesService;

    public UbicacionController(
            CrearUbicacionService crearUbicacionService,
            ListarUbicacionesService listarUbicacionesService)
    {
        this.crearUbicacionService = crearUbicacionService;
        this.listarUbicacionesService = listarUbicacionesService;
    }

    @PostMapping
    public Ubicacion crear(@RequestBody UbicacionRequest request) {
        return crearUbicacionService.crear(request);
    }

    @GetMapping("/listar")
    public List<Ubicacion> listar() {
        return listarUbicacionesService.listar();
    }
}