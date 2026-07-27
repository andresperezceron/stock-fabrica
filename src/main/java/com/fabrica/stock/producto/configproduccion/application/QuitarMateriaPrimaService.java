package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ConfigProduccionNoExisteException;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class QuitarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;

    public QuitarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService) {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
    }

    public void quitar(Producto producto, Producto materiaPrima) {
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(producto);
        config.quitarMateriaPrima(materiaPrima);
    }
}
