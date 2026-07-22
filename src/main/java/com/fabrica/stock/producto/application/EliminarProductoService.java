package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.configproduccion.application.ObtenerConfigProduccionService;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EliminarProductoService {
    private final ProductoRepository productoRepository;
    private final ConfigProduccionRepository configProduccionRepository;
    private final ObtenerProductoService obtenerProductoService;
    private final ComprobarUsoProductoService comprobarUsoProductoService;
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;

    public EliminarProductoService(ProductoRepository productoRepository,
                                   ConfigProduccionRepository configProduccionRepository,
                                   ObtenerProductoService obtenerProductoService,
                                   ComprobarUsoProductoService comprobarUsoProductoService,
                                   ObtenerConfigProduccionService obtenerConfigProduccionService)
    {
        this.productoRepository = productoRepository;
        this.configProduccionRepository = configProduccionRepository;
        this.obtenerProductoService = obtenerProductoService;
        this.comprobarUsoProductoService = comprobarUsoProductoService;
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
    }

    @Transactional
    public void eliminar(Long id) {
        Producto producto = obtenerProductoService.obtener(id);
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(producto);

        if(comprobarUsoProductoService.tieneUso(producto)) {
            producto.desactivar();
            config.desactivar();
        } else {
            productoRepository.delete(producto);
            configProduccionRepository.delete(config);
        }
    }
}