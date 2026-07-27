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

    public void crearPorProducto(Producto producto) {
        if(!configProduccionRepository.existsConfigProduccionByProducto(producto)) {
            configProduccionRepository.save(new ConfigProduccion(producto));
            //aqui lanzariamos una excepcion, es totalmente natural pensar que como se ha creado por
            //primera vez, es una manera muy interesante de recordale al operario que tiene que
            //configurar las materias primas. Si no la crea directamente le pasa la responsbilidad a
            //configproducion.
        }
    }
}
