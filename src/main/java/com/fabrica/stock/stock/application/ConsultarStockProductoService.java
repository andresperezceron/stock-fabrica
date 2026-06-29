package com.fabrica.stock.stock.application;

import com.fabrica.stock.lote.api.dto.StockLoteResponse;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.stock.api.dto.StockProductoResponse;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.domain.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultarStockProductoService {
    private final ProductoRepository productoRepository;
    private final LoteRepository loteRepository;
    private final StockService stockService;

    public ConsultarStockProductoService(
            ProductoRepository productoRepository,
            LoteRepository loteRepository,
            StockService stockService ) {
        this.productoRepository = productoRepository;
        this.loteRepository = loteRepository;
        this.stockService = stockService;
    }

    public StockProductoResponse consultar(Long productoId) {
        Producto producto = productoRepository
                .findById(productoId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Producto no encontrado: " + productoId
                ));

        List<Lote> lotes = loteRepository.findByProductoId(productoId);

        StockProductoResponse response = new StockProductoResponse();
        response.productoId = producto.getId();
        response.codigo = producto.getCodigo();
        response.descripcion = producto.getDescripcion();

        BigDecimal stockTotal = BigDecimal.ZERO;
        List<StockLoteResponse> lotesResponse = new ArrayList<>();

        for(Lote lote : lotes) {
            BigDecimal stockLote = stockService.calcularStockPorLote(lote.getId());
            StockLoteResponse loteResponse = new StockLoteResponse();
            loteResponse.loteId = lote.getId();
            loteResponse.codigoLote = lote.getCodigoLote();
            loteResponse.fechaCreacion = lote.getFechaCreacion();
            loteResponse.stock = stockLote;
            lotesResponse.add(loteResponse);
            stockTotal = stockTotal.add(stockLote);
        }

        response.stockTotal = stockTotal;
        response.lotes = lotesResponse;

        return response;
    }
}