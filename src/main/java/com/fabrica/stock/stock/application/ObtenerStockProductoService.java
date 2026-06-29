package com.fabrica.stock.stock.application;

import com.fabrica.stock.lote.api.dto.StockLoteResponse;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.stock.api.dto.StockProductoResponse;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObtenerStockProductoService {
    private final LoteRepository loteRepository;
    private final StockService stockService;
    private final ObtenerProductoService obtenerProductoService;

    public ObtenerStockProductoService(
            LoteRepository loteRepository,
            StockService stockService,
            ObtenerProductoService obtenerProductoService)
    {
        this.loteRepository = loteRepository;
        this.stockService = stockService;
        this.obtenerProductoService = obtenerProductoService;
    }

    public StockProductoResponse ejecutar(Long productoId) {
        Producto producto = obtenerProductoService.ejecutar(productoId);

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