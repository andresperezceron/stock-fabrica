package com.fabrica.stock.movimiento.api;

import com.fabrica.stock.movimiento.api.dto.MovimientoRequest;
import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    private final CrearMovimientoService crearMovimientoService;

    public MovimientoController(CrearMovimientoService crearMovimientoService) {
        this.crearMovimientoService = crearMovimientoService;
    }

    @PostMapping
    public MovimientoStock crear(@RequestBody MovimientoRequest request) {
       return crearMovimientoService.ejecutar(request);
    }
}
