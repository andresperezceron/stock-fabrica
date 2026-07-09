package com.fabrica.stock.movimiento.api;

import com.fabrica.stock.movimiento.api.dto.ConsultarTrazabilidadLoteRequest;
import com.fabrica.stock.movimiento.api.dto.ConsultarTrazabilidadLoteResponse;
import com.fabrica.stock.movimiento.application.ConsultarTrazabilidadLoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    private final ConsultarTrazabilidadLoteService consultarTrazabilidadLoteService;

    public MovimientoController(ConsultarTrazabilidadLoteService consultarTrazabilidadLoteService) {
        this.consultarTrazabilidadLoteService = consultarTrazabilidadLoteService;
    }

    public ConsultarTrazabilidadLoteResponse consultar(ConsultarTrazabilidadLoteRequest request) {
        return consultarTrazabilidadLoteService.consultar(request);
    }
}