package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.configproduccion.domain.exceptions.ConfigProduccionNoExisteException;
import com.fabrica.stock.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ObtenerConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;

    public ObtenerConfigProduccionService(ConfigProduccionRepository configProduccionRepository) {
        this.configProduccionRepository = configProduccionRepository;
    }

    public ConfigProduccion porProducto(Producto producto) {
        return configProduccionRepository.findConfigProduccionByProducto(producto)
                .orElseThrow(() -> new ConfigProduccionNoExisteException("Configuración de producción no encontrada."));
    }
}