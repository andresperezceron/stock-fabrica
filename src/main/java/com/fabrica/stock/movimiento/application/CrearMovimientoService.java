package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.movimiento.api.dto.MovimientoRequest;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.shared.domain.MovimientoStockNegativoException;
import com.fabrica.stock.shared.domain.RecursoNoEncontradoException;
import com.fabrica.stock.stock.application.StockService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CrearMovimientoService {
    private final StockService stockService;
    private final MovimientoStockRepository movimientoStockRepository;
    private final LoteRepository loteRepository;

    public CrearMovimientoService(
            StockService stockService,
            MovimientoStockRepository movimientoStockRepository,
            LoteRepository loteRepository)
    {
        this.stockService = stockService;
        this.movimientoStockRepository = movimientoStockRepository;
        this.loteRepository = loteRepository;
    }

    public MovimientoStock ejecutar(MovimientoRequest request) {

        Lote lote = loteRepository
                .findById(request.loteId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Lote no encontrado: " + request.loteId
                ));

        TipoMovimiento tipo = TipoMovimiento.valueOf(request.tipo);

        BigDecimal stockActual = stockService.calcularStockPorLote(request.loteId);
        BigDecimal cantidad = stockService.calcularStockPorLote(request.loteId);

        if(tipo == TipoMovimiento.SALIDA && cantidad.compareTo(stockActual) > 0) {
            throw new MovimientoStockNegativoException("Cantidad negativo");
        }

        MovimientoStock movimiento = new MovimientoStock(
                lote,
                tipo,
                request.cantidad,
                LocalDateTime.now()
        );
        return movimientoStockRepository.save(movimiento);
    }
}