package com.fabrica.stock.ubicacion.api;

import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.application.CrearUbicacionService;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionController {
    private final CrearUbicacionService crearUbicacionService;

    public UbicacionController(CrearUbicacionService crearUbicacionService) {
        this.crearUbicacionService = crearUbicacionService;
    }

    @PostMapping
    public Ubicacion crear(@RequestBody UbicacionRequest request) {
        return crearUbicacionService.ejecutar(request);
    }
}