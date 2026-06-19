package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.api.dto.LoteRequest;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.domain.RecursoNoEncontradoException;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CrearLoteService {

    private final LoteRepository loteRepository;
    private final ProductoRepository productoRepository;
    private final MovimientoStockRepository movimientoRepository;

    public CrearLoteService(
            LoteRepository loteRepository,
            ProductoRepository productoRepository,
            MovimientoStockRepository movimientoRepository
    ) {
        this.loteRepository = loteRepository;
        this.productoRepository = productoRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    public Lote ejecutar(LoteRequest request) {

        Producto producto = productoRepository
                .findById(request.productoId)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Producto no encontrado: "
                                        + request.productoId
                        )
                );

        Lote lote = new Lote(
                request.codigoLote,
                OrigenLote.valueOf(request.origen),
                request.loteProveedor,
                producto,
                LocalDateTime.now(),
                request.cantidadInicial
        );

        loteRepository.save(lote);

        MovimientoStock movimientoInicial =
                new MovimientoStock(
                        lote,
                        TipoMovimiento.ENTRADA,
                        request.cantidadInicial,
                        LocalDateTime.now()
                );

        movimientoRepository.save(movimientoInicial);
        return lote;
    }
}