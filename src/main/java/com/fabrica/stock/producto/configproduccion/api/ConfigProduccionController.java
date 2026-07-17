package com.fabrica.stock.producto.configproduccion.api;

import com.fabrica.stock.producto.configproduccion.api.dto.CrearConfigProduccionRequest;
import com.fabrica.stock.producto.configproduccion.application.CrearConfigProduccionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prductos/config-produccion")
public class ConfigProduccionController {
    private final CrearConfigProduccionService crearConfigProduccionService;

    public ConfigProduccionController(CrearConfigProduccionService crearConfigProduccionService) {
        this.crearConfigProduccionService = crearConfigProduccionService;
    }

    @PostMapping
    public void crear(@Valid @RequestBody CrearConfigProduccionRequest request) {
        crearConfigProduccionService.crear(request);
    }
}