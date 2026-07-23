package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class QuitarMateriaPrimaService {
    private final ObtenerOCrearConfigProduccionService obtenerOCrearConfigProduccionService;

    public QuitarMateriaPrimaService(ObtenerOCrearConfigProduccionService obtenerOCrearConfigProduccionService) {
        this.obtenerOCrearConfigProduccionService = obtenerOCrearConfigProduccionService;
    }

    public void quitar(Producto producto, Producto materiaPrima) {
        obtenerOCrearConfigProduccionService
                .obtenerOCrearPorProducto(producto)
                .quitarMateriaPrima(materiaPrima);
    }
}
