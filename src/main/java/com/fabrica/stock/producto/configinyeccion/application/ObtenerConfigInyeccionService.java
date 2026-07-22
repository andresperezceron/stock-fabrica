package com.fabrica.stock.producto.configinyeccion.application;

import com.fabrica.stock.producto.configinyeccion.domain.ConfigInyeccion;
import com.fabrica.stock.producto.configinyeccion.domain.exceptions.NoAdmiteConfigInyeccionException;
import com.fabrica.stock.producto.configinyeccion.infrastructure.ConfigInyeccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ObtenerConfigInyeccionService {
    private final ConfigInyeccionRepository configInyeccionRepository;

    public ObtenerConfigInyeccionService(ConfigInyeccionRepository configInyeccionRepository) {
        this.configInyeccionRepository = configInyeccionRepository;
    }

    public ConfigInyeccion obtenerPorProducto(Producto producto) {
        return configInyeccionRepository.findConfigProduccionByProductoId(producto.getId()).orElseThrow(() ->
                new NoAdmiteConfigInyeccionException("El producto: " + producto.getDescripcion() +
                        " no Admite configuración de inyección."));
    }
}