package com.fabrica.stock.producto.configproduccion.api;

import com.fabrica.stock.producto.configproduccion.api.dto.ActualizarConfigProduccionRequest;
import com.fabrica.stock.producto.configproduccion.application.ActualizarConfigProduccionService;
import com.fabrica.stock.producto.configproduccion.application.CrearConfigProduccionService;
import com.fabrica.stock.producto.configproduccion.application.ListarConfigProduccionService;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos/config-produccion")
public class ConfigProduccionController {
    private final CrearConfigProduccionService crearConfigProduccionService;
    private final ListarConfigProduccionService listarConfigProduccionService;
    private final ActualizarConfigProduccionService actualizarConfigProduccionService;

    public ConfigProduccionController(CrearConfigProduccionService crearConfigProduccionService,
                                      ListarConfigProduccionService listarConfigProduccionService,
                                      ActualizarConfigProduccionService actualizarConfigProduccionService)
    {
        this.crearConfigProduccionService = crearConfigProduccionService;
        this.listarConfigProduccionService = listarConfigProduccionService;
        this.actualizarConfigProduccionService = actualizarConfigProduccionService;
    }

    @GetMapping
    public List<ConfigProduccion> listar() { return listarConfigProduccionService.listar(); }

    @PostMapping
    public void crear(@PathVariable Long productoId) {
        crearConfigProduccionService.crear(productoId);
    }

    @PutMapping
    public void actualizar(@Valid @RequestBody ActualizarConfigProduccionRequest request) {
        actualizarConfigProduccionService.actualizar(request);
    }
}