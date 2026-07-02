package com.fabrica.stock.movimiento.api;

import com.fabrica.stock.movimiento.api.dto.*;
import com.fabrica.stock.movimiento.application.PrepararSalidaProductoService;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    private final PrepararSalidaProductoService prepararSalidaProductoService;

    public MovimientoController(PrepararSalidaProductoService prepararSalidaProductoService) {
        this.prepararSalidaProductoService = prepararSalidaProductoService;
    }

    @PostMapping
    public MovimientoStock crear(@RequestBody MovimientoRequest request) {
       return null;
    }

    @PostMapping("/salida/preparar")
    public PrepararSalidaResponse prepararSalida(@RequestBody PrepararSalidaRequest request) {
        return prepararSalidaProductoService.prepararSalida(request);
    }

    @PostMapping("/salida/confirmar")
    public ConfirmarSalidaResponse confirmarSalida(@RequestBody ConfirmarSalidaRequest request) {
        return null;
    }
}