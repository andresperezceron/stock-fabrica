package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.movimiento.api.dto.LoteSalidaResponse;
import com.fabrica.stock.movimiento.api.dto.PrepararSalidaRequest;
import com.fabrica.stock.movimiento.api.dto.PrepararSalidaResponse;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.domain.RecursoNoEncontradoException;
import com.fabrica.stock.stock.application.StockService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PrepararSalidaProductoService {
    private final ProductoRepository productoRepository;
    private final LoteRepository loteRepository;
    private final StockService stockService;

    public PrepararSalidaProductoService(
            ProductoRepository productoRepository,
            LoteRepository loteRepository,
            StockService stockService)
    {
        this.productoRepository = productoRepository;
        this.loteRepository = loteRepository;
        this.stockService = stockService;
    }

    public PrepararSalidaResponse prepararSalida(PrepararSalidaRequest request) {
        Producto producto = productoRepository
                .findById(request.productoId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Producto no encontrado: " + request.productoId
                ));
        BigDecimal cantidadSolicitada = request.cantidadSolicitada;
        BigDecimal cantidadPendiente = cantidadSolicitada;

        List<Lote> lotes = loteRepository.findByProductoId(producto.getId());
        lotes.sort(Comparator.comparing(Lote::getFechaCreacion));
        List<LoteSalidaResponse> lotesSalida = new ArrayList<>();

        for(Lote lote : lotes) {
            LoteSalidaResponse response = new LoteSalidaResponse();
            response.loteId = lote.getId();
            response.codigoLote = lote.getCodigoLote();
            response.fechaCreacion =  lote.getFechaCreacion();
            response.stockDisponible = stockService.calcularStockPorLote(lote.getId());
            //response.cantidadAsignada = response.stockDisponible - cantidadPendiente;
        }

        return null;
    }
}
