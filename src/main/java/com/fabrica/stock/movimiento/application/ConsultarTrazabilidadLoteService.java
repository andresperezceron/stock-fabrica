package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.lote.application.ObtenerLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.movimiento.api.dto.ConsultarTrazabilidadLoteResponse;
import com.fabrica.stock.movimiento.api.dto.MovimientoResponse;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import org.springframework.stereotype.Service;


@Service
public class ConsultarTrazabilidadLoteService {
    private final MovimientoStockRepository movimientoStockRepository;
    private final ObtenerLoteService obtenerLoteService;

    public ConsultarTrazabilidadLoteService(MovimientoStockRepository movimientoStockRepository,
                                            ObtenerLoteService obtenerLoteService)
    {
        this.movimientoStockRepository = movimientoStockRepository;
        this.obtenerLoteService = obtenerLoteService;
    }

    public ConsultarTrazabilidadLoteResponse consultar(Long loteId) {
        Lote lote = obtenerLoteService.obtener(loteId);

        return new ConsultarTrazabilidadLoteResponse(
                lote,
                movimientoStockRepository
                        .findAllByLoteOrderByFechaAsc(lote)
                        .stream()
                        .map(MovimientoResponse::new)
                        .toList()
        );
    }
}