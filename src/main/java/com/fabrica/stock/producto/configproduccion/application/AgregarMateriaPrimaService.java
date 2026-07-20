package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class AgregarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ObtenerProductoService obtenerProductoService;

    public AgregarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                      ObtenerProductoService obtenerProductoService)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerProductoService = obtenerProductoService;
    }

    public void agregar(Long productoId) {
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(productoId);
        Producto producto = obtenerProductoService.obtener(productoId);
        config.agregarMateriaPrima(producto);
    }
}