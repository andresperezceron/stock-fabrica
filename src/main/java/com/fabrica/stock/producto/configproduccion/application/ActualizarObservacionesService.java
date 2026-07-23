package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ActualizarObservacionesService {
    private final ObtenerOCrearConfigProduccionService obtenerOCrearConfigProduccionService;

    public ActualizarObservacionesService(ObtenerOCrearConfigProduccionService obtenerOCrearConfigProduccionService) {
        this.obtenerOCrearConfigProduccionService = obtenerOCrearConfigProduccionService;
    }

    public void actualizar(Producto producto, String observaciones) {
        obtenerOCrearConfigProduccionService
                .obtenerOCrearPorProducto(producto)
                .actualizarObservaciones(observaciones);
    }
}