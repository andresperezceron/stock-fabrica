package com.fabrica.stock.producto.configinyeccion.application;

import com.fabrica.stock.producto.configinyeccion.domain.ConfigInyeccion;
import com.fabrica.stock.producto.configinyeccion.infrastructure.ConfigInyeccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class CrearConfigInyeccionService {
    private final ConfigInyeccionRepository configInyeccionRepository;

    public CrearConfigInyeccionService(ConfigInyeccionRepository configInyeccionRepository) {
        this.configInyeccionRepository = configInyeccionRepository;
    }

    public void crear(Producto producto) {
        configInyeccionRepository.save(new ConfigInyeccion(producto));
    }
}