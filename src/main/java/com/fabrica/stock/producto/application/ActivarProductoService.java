package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.configinyeccion.application.ObtenerConfigInyeccionService;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActivarProductoService {
    private final ObtenerProductoInactivoService obtenerProductoInactivoService;
    private final ObtenerConfigInyeccionService obtenerConfigInyeccionService;

    public ActivarProductoService(ObtenerProductoInactivoService obtenerProductoInactivoService,
                                  ObtenerConfigInyeccionService obtenerConfigInyeccionService)
    {
        this.obtenerProductoInactivoService = obtenerProductoInactivoService;
        this.obtenerConfigInyeccionService = obtenerConfigInyeccionService;
    }

    @Transactional
    public void activar(Long id) {
        Producto producto = obtenerProductoInactivoService.obtener(id);
        producto.activar();
        if(producto.admiteConfigProduccion()) {
            obtenerConfigInyeccionService.obtenerPorProducto(producto).activar();
        }

    }
}