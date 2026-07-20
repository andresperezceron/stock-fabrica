package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class EliminarProductoService {
    private final ProductoRepository productoRepository;
    private final ObtenerProductoService obtenerProductoService;
    private final ComprobarUsoProductoService comprobarUsoProductoService;

    public EliminarProductoService(ProductoRepository productoRepository,
                                   ObtenerProductoService obtenerProductoService,
                                   ComprobarUsoProductoService comprobarUsoProductoService)
    {
        this.productoRepository = productoRepository;
        this.obtenerProductoService = obtenerProductoService;
        this.comprobarUsoProductoService = comprobarUsoProductoService;
    }

    public void eliminar(Long id) {
        Producto producto = obtenerProductoService.obtener(id);
        if(comprobarUsoProductoService.tieneUso(producto)) {
            producto.desactivar();
        } else productoRepository.delete(producto);
    }
}