package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class CrearConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;

    public CrearConfigProduccionService(ConfigProduccionRepository configProduccionRepository) {
        this.configProduccionRepository = configProduccionRepository;
    }

    public ConfigProduccion porProductoSiNoExiste(Producto producto) {
        return configProduccionRepository
                .findConfigProduccionByProducto(producto)
                .orElseGet(() -> configProduccionRepository.save(new ConfigProduccion(producto)));
    }
}