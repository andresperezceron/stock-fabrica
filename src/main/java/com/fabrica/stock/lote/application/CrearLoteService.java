package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.api.dto.CrearLoteRequest;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.domain.validation.CrearLoteValidator;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CrearLoteService {
    private final LoteRepository loteRepository;
    private final MovimientoStockRepository movimientoRepository;
    private final ProductoRepository productoRepository;
    private final UbicacionRepository ubicacionRepository;

    public CrearLoteService(
            LoteRepository loteRepository,
            MovimientoStockRepository movimientoRepository,
            ProductoRepository productoRepository,
            UbicacionRepository ubicacionRepository
    ) {
        this.loteRepository = loteRepository;
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
        this.ubicacionRepository = ubicacionRepository;
    }

    @Transactional
    public Lote ejecutar(CrearLoteRequest request) {
        new CrearLoteValidator(request, loteRepository, productoRepository, ubicacionRepository).validate();

        Lote lote = new Lote(
                request.codigoLote,
                request.origenLote,
                request.loteProveedor,
                productoRepository.findById(request.productoId).orElse(null),
                LocalDateTime.now()
        );

        loteRepository.save(lote);

        MovimientoStock movimientoInicial =
                new MovimientoStock(
                        lote,
                        TipoMovimiento.ENTRADA,
                        BigDecimal.ZERO, /* Esto esta sin implementar */
                        LocalDateTime.now(),
                        ubicacionRepository.findById(request.origenId).orElse(null),
                        ubicacionRepository.findById(request.destinoId).orElse(null)
                );

        movimientoRepository.save(movimientoInicial);
        return lote;
    }
}