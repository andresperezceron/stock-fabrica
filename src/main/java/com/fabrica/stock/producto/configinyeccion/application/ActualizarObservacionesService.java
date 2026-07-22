package com.fabrica.stock.producto.configinyeccion.application;

import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ActualizarObservacionesService {
    private final ObtenerConfigInyeccionService obtenerConfigInyeccionService;

    public ActualizarObservacionesService(ObtenerConfigInyeccionService obtenerConfigInyeccionService) {
        this.obtenerConfigInyeccionService = obtenerConfigInyeccionService;
    }

    public void actualizar(Producto producto, String observaciones) {

    }
}
