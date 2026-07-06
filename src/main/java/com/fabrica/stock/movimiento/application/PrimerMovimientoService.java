package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PrimerMovimientoService {
    private final MovimientoStockRepository movimientoStockRepository;

    public PrimerMovimientoService(MovimientoStockRepository movimientoStockRepository) {
        this.movimientoStockRepository = movimientoStockRepository;
    }

    public MovimientoStock primerMovimiento(Lote lote, LocalDateTime fecha) {
        return null;
    }
}
