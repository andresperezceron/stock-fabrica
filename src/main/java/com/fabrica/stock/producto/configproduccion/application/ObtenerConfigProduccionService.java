package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ConfigProduccionNoEncontradaException;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ObtenerConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;

    public ObtenerConfigProduccionService(ConfigProduccionRepository configProduccionRepository) {
        this.configProduccionRepository = configProduccionRepository;
    }

    public ConfigProduccion obtenerPorProducto(Producto producto) {
        return configProduccionRepository.findConfigProduccionByProductoId(producto.getId()).orElseThrow(() ->
                new ConfigProduccionNoEncontradaException("El producto: " + producto.getDescripcion() +
                        " no Admite configuradción de produccion."));
    }
}