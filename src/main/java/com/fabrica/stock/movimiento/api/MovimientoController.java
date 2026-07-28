package com.fabrica.stock.movimiento.api;

import com.fabrica.stock.movimiento.api.dto.ConsultarTrazabilidadLoteResponse;
import com.fabrica.stock.movimiento.application.ConsultarTrazabilidadLoteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    private final ConsultarTrazabilidadLoteService consultarTrazabilidadLoteService;

    public MovimientoController(ConsultarTrazabilidadLoteService consultarTrazabilidadLoteService) {
        this.consultarTrazabilidadLoteService = consultarTrazabilidadLoteService;
    }

    @GetMapping("/lotes/{maquinaId}")
    public ConsultarTrazabilidadLoteResponse consultar(@PathVariable Long id) {
        return consultarTrazabilidadLoteService.consultar(id);
    }
}