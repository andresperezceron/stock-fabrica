package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;

    public ObtenerConfigProduccionService(ConfigProduccionRepository configProduccionRepository) {
        this.configProduccionRepository = configProduccionRepository;
    }

    public ConfigProduccion obtener(Long id) {
        return configProduccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el config produccion"));
    }
}