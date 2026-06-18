package com.fabrica.stock.stock.application;

import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockService {

    private final MovimientoStockRepository movimientoRepo;

    public StockService(MovimientoStockRepository movimientoRepo) {
        this.movimientoRepo = movimientoRepo;
    }

    public BigDecimal calcularStockPorLote(Long loteId) {

        List<MovimientoStock> movimientos =
                movimientoRepo.findByLoteId(loteId);

        BigDecimal stock = BigDecimal.ZERO;

        for (MovimientoStock m : movimientos) {

            if (m.getTipo() == TipoMovimiento.ENTRADA) {
                stock = stock.add(m.getCantidad());
            } else {
                stock = stock.subtract(m.getCantidad());
            }
        }

        return stock;
    }
}