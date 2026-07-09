package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.lote.application.ObtenerLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.movimiento.api.dto.ConsultarTrazabilidadLoteRequest;
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

    public ConsultarTrazabilidadLoteResponse consultar(ConsultarTrazabilidadLoteRequest request) {
        Lote lote = obtenerLoteService.obtener(request.loteId);
        ConsultarTrazabilidadLoteResponse response = new ConsultarTrazabilidadLoteResponse();
        response.codigoLote = lote.getCodigoLote();
        response.fechaProduccion = lote.getFechaCreacion();
        response.movimientos = movimientoStockRepository
                .findAllByLoteOrderByFechaAsc(lote)
                .stream()
                .map(MovimientoResponse::new)
                .toList();

        return response;
    }
}