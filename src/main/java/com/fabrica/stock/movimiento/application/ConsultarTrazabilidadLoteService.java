package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.movimiento.api.dto.ConsultarTrazabilidadLoteRequest;
import com.fabrica.stock.movimiento.api.dto.ConsultarTrazabilidadLoteResponse;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultarTrazabilidadLoteService {
    private final MovimientoStockRepository movimientoStockRepository;

    public ConsultarTrazabilidadLoteService(MovimientoStockRepository movimientoStockRepository) {
        this.movimientoStockRepository = movimientoStockRepository;
    }

    public ConsultarTrazabilidadLoteResponse consultar(ConsultarTrazabilidadLoteRequest request) {
        return null;
    }

}
