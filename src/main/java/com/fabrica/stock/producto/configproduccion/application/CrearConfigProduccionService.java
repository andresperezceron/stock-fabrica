package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.lote.application.ObtenerLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.configproduccion.api.dto.CrearConfigProduccionRequest;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.validation.CrearConfigProduccionValidation;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrearConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;
    private final LoteRepository loteRepository;
    private final ProductoRepository productoRepository;
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerLoteService obtenerLoteService;

    public CrearConfigProduccionService(ConfigProduccionRepository configProduccionRepository,
                                        LoteRepository loteRepository,
                                        ProductoRepository productoRepository,
                                        ObtenerProductoService obtenerProductoService,
                                        ObtenerLoteService obtenerLoteService)
    {
        this.configProduccionRepository = configProduccionRepository;
        this.loteRepository = loteRepository;
        this.productoRepository = productoRepository;
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerLoteService = obtenerLoteService;
    }

    public void crear(CrearConfigProduccionRequest request) {
        new CrearConfigProduccionValidation(request, configProduccionRepository, productoRepository).validate();
        Producto producto = obtenerProductoService.obtener(request.productoId);
        List<Lote> lotesMateriaPrima = request.lotesMateriaPrima.stream()
                .map(obtenerLoteService::obtener)
                .toList();

        configProduccionRepository.save(new ConfigProduccion(
                producto,
                lotesMateriaPrima,
                request.observaciones
        ));
    }
}