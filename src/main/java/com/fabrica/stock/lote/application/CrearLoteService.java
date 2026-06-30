package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.api.dto.LoteRequest;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.RecursoNoEncontradoException;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CrearLoteService {
    private final LoteRepository loteRepository;
    private final MovimientoStockRepository movimientoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final ObtenerLoteService obtenerLoteService;
    private final ObtenerProductoService obtenerProductoService;

    //private final ObtenerProductoService obtenerProductoService;

    public CrearLoteService(
            LoteRepository loteRepository,
            MovimientoStockRepository movimientoRepository,
            UbicacionRepository ubicacionRepository,
            ObtenerLoteService obtenerLoteService,
            ObtenerProductoService obtenerProductoService
    ) {
        this.loteRepository = loteRepository;
        this.movimientoRepository = movimientoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.obtenerLoteService = obtenerLoteService;
        this.obtenerProductoService = obtenerProductoService;
    }

    @Transactional
    public Lote ejecutar(LoteRequest request) {

        Ubicacion origen = ubicacionRepository
                .findById(request.origenId)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Origen indicado no existente. ")
                );
        Ubicacion destino = ubicacionRepository
                .findById(request.destinoId)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Destino indicado no existente. ")
                );

        Lote lote = new Lote(
                request.codigoLote,
                OrigenLote.valueOf(request.origenLote),
                request.loteProveedor,
                obtenerProductoService.obtener(request.productoId),
                LocalDateTime.now()
        );

        loteRepository.save(lote);

        MovimientoStock movimientoInicial =
                new MovimientoStock(
                        lote,
                        TipoMovimiento.ENTRADA,
                        request.cantidadInicial,
                        LocalDateTime.now(),
                        origen,
                        destino
                );

        movimientoRepository.save(movimientoInicial);
        return lote;
    }
}