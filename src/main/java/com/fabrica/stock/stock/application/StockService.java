package com.fabrica.stock.stock.application;

import com.fabrica.stock.movimiento.domain.Movimiento;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockService {

    private final MovimientoRepository movimientoRepo;

    public StockService(MovimientoRepository movimientoRepo) {
        this.movimientoRepo = movimientoRepo;
    }

    public BigDecimal calcularStockPorLote(Long loteId) {
        List<Movimiento> movimientos = movimientoRepo.findByLoteId(loteId);
        BigDecimal stock = BigDecimal.ZERO;

        for(Movimiento m : movimientos) {
            if (m.getTipo() == TipoMovimiento.ENTRADA) {
                stock = stock.add(m.getCantidad());
            } else {
                stock = stock.subtract(m.getCantidad());
            }
        }

        return stock;
    }
}