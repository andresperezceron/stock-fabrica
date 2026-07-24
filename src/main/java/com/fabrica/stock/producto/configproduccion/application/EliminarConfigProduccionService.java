package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class EliminarConfigProduccionService {
    private final ConfigProduccionRepository configProduccionRepository;
    private final ObtenerOCrearConfigProduccionService obtenerOCrearConfigProduccionService;

    public EliminarConfigProduccionService(ConfigProduccionRepository configProduccionRepository,
                                           ObtenerOCrearConfigProduccionService obtenerOCrearConfigProduccionService)
    {
        this.configProduccionRepository = configProduccionRepository;
        this.obtenerOCrearConfigProduccionService = obtenerOCrearConfigProduccionService;
    }

    public void eliminar(Producto producto, boolean tieneUso) {
        ConfigProduccion config = obtenerOCrearConfigProduccionService.obtenerOCrearPorProducto(producto);
        if(tieneUso) configProduccionRepository.delete(config);
    }
}