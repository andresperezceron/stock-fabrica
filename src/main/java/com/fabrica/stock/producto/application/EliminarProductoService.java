package com.fabrica.stock.producto.application;

import com.fabrica.stock.configproduccion.application.EliminarConfigProduccionService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EliminarProductoService {
    private final ProductoRepository productoRepository;
    private final ObtenerProductoService obtenerProductoService;
    private final ComprobarUsoProductoService comprobarUsoProductoService;
    private final EliminarConfigProduccionService eliminarConfigProduccionService;

    public EliminarProductoService(ProductoRepository productoRepository,
                                   ObtenerProductoService obtenerProductoService,
                                   ComprobarUsoProductoService comprobarUsoProductoService,
                                   EliminarConfigProduccionService eliminarConfigProduccionService)
    {
        this.productoRepository = productoRepository;
        this.obtenerProductoService = obtenerProductoService;
        this.comprobarUsoProductoService = comprobarUsoProductoService;
        this.eliminarConfigProduccionService = eliminarConfigProduccionService;
    }

    @Transactional
    public void eliminar(Long id) {
        Producto producto = obtenerProductoService.obtener(id);
        if(comprobarUsoProductoService.tieneUso(producto)) {
            producto.desactivar();
            return;
        }
        eliminarConfigProduccionService.porProducto(producto);
        productoRepository.delete(producto);
    }
}