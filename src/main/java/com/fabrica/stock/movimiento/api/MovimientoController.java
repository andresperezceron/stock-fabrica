package com.fabrica.stock.movimiento.api;

import com.fabrica.stock.movimiento.api.dto.MovimientoRequest;
import com.fabrica.stock.movimiento.api.dto.PrepararSalidaRequest;
import com.fabrica.stock.movimiento.api.dto.PrepararSalidaResponse;
import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.movimiento.application.PrepararSalidaProductoService;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    private final CrearMovimientoService crearMovimientoService;
    private final PrepararSalidaProductoService prepararSalidaProductoService;

    public MovimientoController(
            CrearMovimientoService crearMovimientoService,
            PrepararSalidaProductoService prepararSalidaProductoService)
    {
        this.crearMovimientoService = crearMovimientoService;
        this.prepararSalidaProductoService = prepararSalidaProductoService;
    }

    @PostMapping
    public MovimientoStock crear(@RequestBody MovimientoRequest request) {
       return crearMovimientoService.ejecutar(request);
    }

    @PostMapping("/salida")
    public PrepararSalidaResponse prepararSalida(@RequestBody PrepararSalidaRequest request) {
        return prepararSalidaProductoService.prepararSalida(request);
    }
}
