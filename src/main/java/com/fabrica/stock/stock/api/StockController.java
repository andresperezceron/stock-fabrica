package com.fabrica.stock.stock.api;

import com.fabrica.stock.stock.api.dto.StockProductoResponse;
import com.fabrica.stock.stock.application.ObtenerStockProductoService;
import com.fabrica.stock.stock.application.StockService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;
    private final ObtenerStockProductoService consultarStockProductoService;

    public StockController(
            StockService stockService,
            ObtenerStockProductoService consultarStockProductoService)
    {
        this.stockService = stockService;
        this.consultarStockProductoService = consultarStockProductoService;
    }

    @GetMapping("/lote/{loteId}")
    public BigDecimal stockPorLote(@PathVariable Long loteId) {
        return stockService.calcularStockPorLote(loteId);
    }

    @GetMapping("/producto/{productoId}")
    public StockProductoResponse stockPorProducto(@PathVariable Long productoId) {
        return consultarStockProductoService.ejecutar(productoId);
    }
}