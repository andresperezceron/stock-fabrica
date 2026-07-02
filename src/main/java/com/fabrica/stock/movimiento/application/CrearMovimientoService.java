package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CrearMovimientoService {
    private final MovimientoStockRepository movimientoRepository;

    public CrearMovimientoService(MovimientoStockRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    public MovimientoStock crear(
            Lote lote,
            TipoMovimiento tipoMovimiento,
            BigDecimal cantidad,
            LocalDateTime fecha,
            Ubicacion origen,
            Ubicacion destino) {

        MovimientoStock movimiento = new MovimientoStock(
                lote,
                tipoMovimiento,
                cantidad,
                fecha,
                origen,
                destino
        );

        return movimientoRepository.save(movimiento);
    }
}