package com.fabrica.stock.lote.api;

import com.fabrica.stock.lote.api.dto.LoteRequest;
import com.fabrica.stock.lote.application.CrearLoteService;
import com.fabrica.stock.lote.domain.Lote;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {

    private final CrearLoteService crearLoteService;

    public LoteController(CrearLoteService crearLoteService) {
        this.crearLoteService = crearLoteService;
    }

    @PostMapping
    public Lote crear(@RequestBody LoteRequest request) {
        return crearLoteService.ejecutar(request);
    }
}