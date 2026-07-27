package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EliminarConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;

    public EliminarConfigProduccionService(ConfigProduccionRepository configProduccionRepository) {
        this.configProduccionRepository = configProduccionRepository;
    }

    public void eliminarPorProducto(Producto producto, boolean tieneUso) {
        Optional<ConfigProduccion> config = configProduccionRepository.findConfigProduccionByProducto(producto);
        if(config.isPresent() && !tieneUso) configProduccionRepository.delete(config.get());
    }
}