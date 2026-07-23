package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.configproduccion.application.EliminarConfigProduccionService;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EliminarProductoService {
    private final ProductoRepository productoRepository;
    private final ObtenerProductoService obtenerProductoService;
    private final ComprobarUsoProductoService comprobarUsoProductoService;
    private final ConfigProduccionRepository configProduccionRepository;
    private final EliminarConfigProduccionService eliminarConfigProduccionService;

    public EliminarProductoService(ProductoRepository productoRepository,
                                   ObtenerProductoService obtenerProductoService,
                                   ComprobarUsoProductoService comprobarUsoProductoService,
                                   ConfigProduccionRepository configProduccionRepository,
                                   EliminarConfigProduccionService eliminarConfigProduccionService)
    {
        this.productoRepository = productoRepository;
        this.obtenerProductoService = obtenerProductoService;
        this.comprobarUsoProductoService = comprobarUsoProductoService;
        this.configProduccionRepository = configProduccionRepository;
        this.eliminarConfigProduccionService = eliminarConfigProduccionService;
    }

    @Transactional
    public void eliminar(Long id) {
        Producto producto = obtenerProductoService.obtener(id);
        boolean tieneUso = comprobarUsoProductoService.tieneUso(producto);
        ConfigProduccion config = configProduccionRepository.findConfigProduccionByProducto(producto);

        if(config != null) eliminarConfigProduccionService.eliminar(producto, tieneUso);

        if(tieneUso) producto.desactivar();
        else productoRepository.delete(producto);
    }
}