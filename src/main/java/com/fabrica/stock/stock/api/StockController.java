package com.fabrica.stock.stock.api;

import com.fabrica.stock.stock.application.StockService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/lote/{loteId}")
    public BigDecimal stockPorLote(@PathVariable Long loteId) {
        return stockService.calcularStockPorLote(loteId);
    }
}