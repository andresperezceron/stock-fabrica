package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ObtenerOCrearConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;
    private final CrearConfigProduccionService crearConfigProduccionService;

    public ObtenerOCrearConfigProduccionService(ConfigProduccionRepository configProduccionRepository,
                                                CrearConfigProduccionService crearConfigProduccionService)
    {
        this.configProduccionRepository = configProduccionRepository;
        this.crearConfigProduccionService = crearConfigProduccionService;
    }

    public ConfigProduccion obtenerOCrearPorProducto(Producto producto) {
        ConfigProduccion config = configProduccionRepository.findConfigProduccionByProducto(producto);
        return config == null ? crearConfigProduccionService.crear(producto) : config;
    }
}