package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class CrearConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;
    private final ObtenerProductoService obtenerProductoService;

    public CrearConfigProduccionService(ConfigProduccionRepository configProduccionRepository,
                                        ObtenerProductoService obtenerProductoService)
    {
        this.configProduccionRepository = configProduccionRepository;
        this.obtenerProductoService = obtenerProductoService;
    }

    public void crear(Long productoId) {
        Producto producto = obtenerProductoService.obtener(productoId);
        configProduccionRepository.save(new ConfigProduccion(producto));
    }
}