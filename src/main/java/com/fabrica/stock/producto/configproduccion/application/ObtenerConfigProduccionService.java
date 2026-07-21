package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ConfigProduccionNoEncontradaException;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;

    public ObtenerConfigProduccionService(ConfigProduccionRepository configProduccionRepository) {
        this.configProduccionRepository = configProduccionRepository;
    }

    public ConfigProduccion obtenerPorProducto(Long productoId) {
        return configProduccionRepository.findConfigProduccionByProductoId(productoId).orElseThrow(() ->
                new ConfigProduccionNoEncontradaException("No existe configuración produccion " +
                        "para el producto con id: " + productoId));
    }
}