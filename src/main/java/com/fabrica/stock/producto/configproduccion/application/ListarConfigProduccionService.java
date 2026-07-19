package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;

    public ListarConfigProduccionService(ConfigProduccionRepository configProduccionRepository) {
        this.configProduccionRepository = configProduccionRepository;
    }

    public List<ConfigProduccion> listar() {
        return configProduccionRepository.findAll();
    }
}