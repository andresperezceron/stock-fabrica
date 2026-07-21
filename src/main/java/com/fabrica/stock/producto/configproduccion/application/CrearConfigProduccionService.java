package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class CrearConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;

    public CrearConfigProduccionService(ConfigProduccionRepository configProduccionRepository) {
        this.configProduccionRepository = configProduccionRepository;
    }

    public void crear(Producto producto) {
        configProduccionRepository.save(new ConfigProduccion(producto));
    }
}