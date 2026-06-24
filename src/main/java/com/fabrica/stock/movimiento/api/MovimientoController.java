package com.fabrica.stock.movimiento.api;

import com.fabrica.stock.movimiento.api.dto.*;
import com.fabrica.stock.movimiento.application.ConfirmarSalidaProductoService;
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
    private final ConfirmarSalidaProductoService confirmarSalidaProductoService;

    public MovimientoController(
            CrearMovimientoService crearMovimientoService,
            PrepararSalidaProductoService prepararSalidaProductoService,
            ConfirmarSalidaProductoService confirmarSalidaProductoService)
    {
        this.crearMovimientoService = crearMovimientoService;
        this.prepararSalidaProductoService = prepararSalidaProductoService;
        this.confirmarSalidaProductoService = confirmarSalidaProductoService;
    }

    @PostMapping
    public MovimientoStock crear(@RequestBody MovimientoRequest request) {
       return crearMovimientoService.ejecutar(request);
    }

    @PostMapping("/salida/preparar")
    public PrepararSalidaResponse prepararSalida(@RequestBody PrepararSalidaRequest request) {
        return prepararSalidaProductoService.prepararSalida(request);
    }

    @PostMapping("/salida/confirmar")
    public ConfirmarSalidaResponse confirmarSalida(@RequestBody ConfirmarSalidaRequest request) {
        return confirmarSalidaProductoService.salida(request);
    }
}