package com.fabrica.stock.stock.api;

import com.fabrica.stock.producto.api.dto.StockProductoResponse;
import com.fabrica.stock.stock.application.ConsultarStockProductoService;
import com.fabrica.stock.stock.application.StockService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;
    private final ConsultarStockProductoService consultarStockProductoService;

    public StockController(
            StockService stockService,
            ConsultarStockProductoService consultarStockProductoService)
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
        return consultarStockProductoService.consultar(productoId);
    }
}