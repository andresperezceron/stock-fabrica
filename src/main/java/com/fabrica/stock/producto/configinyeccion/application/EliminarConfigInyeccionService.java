package com.fabrica.stock.producto.configinyeccion.application;

import com.fabrica.stock.producto.configinyeccion.domain.ConfigInyeccion;
import com.fabrica.stock.producto.configinyeccion.infrastructure.ConfigInyeccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class EliminarConfigInyeccionService {
    private final ConfigInyeccionRepository configInyeccionRepository;
    private final ObtenerConfigInyeccionService obtenerConfigInyeccionService;

    public EliminarConfigInyeccionService(ConfigInyeccionRepository configInyeccionRepository,
                                          ObtenerConfigInyeccionService obtenerConfigInyeccionService)
    {
        this.configInyeccionRepository = configInyeccionRepository;
        this.obtenerConfigInyeccionService = obtenerConfigInyeccionService;
    }

    public void eliminar(Producto producto, boolean tieneUso) {
        ConfigInyeccion config = obtenerConfigInyeccionService.obtenerPorProducto(producto);
        if(tieneUso) config.desactivar();
        else configInyeccionRepository.delete(config);
    }
}