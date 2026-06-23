package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.stock.application.StockService;
import org.springframework.stereotype.Service;

@Service
public class ConsultarHistorialMovimientosService {
    private final StockService stockService;
    private final MovimientoStockRepository movimientoStockRepository;
    private final LoteRepository loteRepository;

    public ConsultarHistorialMovimientosService(
            StockService stockService,
            MovimientoStockRepository movimientoStockRepository,
            LoteRepository loteRepository)
    {
        this.stockService = stockService;
        this.movimientoStockRepository = movimientoStockRepository;
        this.loteRepository = loteRepository;
    }
}
