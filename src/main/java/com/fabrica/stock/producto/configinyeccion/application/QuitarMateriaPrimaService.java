package com.fabrica.stock.producto.configinyeccion.application;

import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class QuitarMateriaPrimaService {
    private final ObtenerConfigInyeccionService obtenerConfigInyeccionService;

    public QuitarMateriaPrimaService(ObtenerConfigInyeccionService obtenerConfigInyeccionService) {
        this.obtenerConfigInyeccionService = obtenerConfigInyeccionService;
    }

    public void quitar(Producto producto, Producto materiaPrima) {
        obtenerConfigInyeccionService
                .obtenerPorProducto(producto)
                .quitarMateriaPrima(materiaPrima);
    }
}
