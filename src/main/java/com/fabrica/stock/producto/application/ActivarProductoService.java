package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.configproduccion.application.ObtenerOCrearConfigProduccionService;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActivarProductoService {
    private final ObtenerProductoInactivoService obtenerProductoInactivoService;
    private final ObtenerOCrearConfigProduccionService obtenerOCrearConfigProduccionService;

    public ActivarProductoService(ObtenerProductoInactivoService obtenerProductoInactivoService,
                                  ObtenerOCrearConfigProduccionService obtenerOCrearConfigProduccionService)
    {
        this.obtenerProductoInactivoService = obtenerProductoInactivoService;
        this.obtenerOCrearConfigProduccionService = obtenerOCrearConfigProduccionService;
    }

    @Transactional
    public void activar(Long id) {
        Producto producto = obtenerProductoInactivoService.obtener(id);
        producto.activar();
        if(producto.admiteConfigProduccion()) {
            obtenerOCrearConfigProduccionService.obtenerOCrearPorProducto(producto).activar();
        }

    }
}