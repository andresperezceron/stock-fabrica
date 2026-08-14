package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class EliminarConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;

    public EliminarConfigProduccionService(ConfigProduccionRepository configProduccionRepository,
                                           ObtenerConfigProduccionService obtenerConfigProduccionService)
    {
        this.configProduccionRepository = configProduccionRepository;
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
    }

    public void porProducto(Producto producto) {
        configProduccionRepository.delete(obtenerConfigProduccionService.porProducto(producto));
    }
}