package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class EliminarConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;

    public EliminarConfigProduccionService(ConfigProduccionRepository configProduccionRepository) {
        this.configProduccionRepository = configProduccionRepository;
    }

    public void porProducto(Producto producto) {
        configProduccionRepository.delete(configProduccionRepository
                .findConfigProduccionByProducto(producto)
                .orElseThrow(() -> new RecursoNoEncontradoException("ConfigProducción no encontrada.")));
    }
}