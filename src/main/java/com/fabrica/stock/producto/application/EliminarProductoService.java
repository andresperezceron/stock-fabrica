package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.configinyeccion.application.EliminarConfigInyeccionService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EliminarProductoService {
    private final ProductoRepository productoRepository;
    private final ObtenerProductoService obtenerProductoService;
    private final ComprobarUsoProductoService comprobarUsoProductoService;
    private final EliminarConfigInyeccionService eliminarConfigInyeccionService;

    public EliminarProductoService(ProductoRepository productoRepository,
                                   ObtenerProductoService obtenerProductoService,
                                   ComprobarUsoProductoService comprobarUsoProductoService,
                                   EliminarConfigInyeccionService eliminarConfigInyeccionService)
    {
        this.productoRepository = productoRepository;
        this.obtenerProductoService = obtenerProductoService;
        this.comprobarUsoProductoService = comprobarUsoProductoService;
        this.eliminarConfigInyeccionService = eliminarConfigInyeccionService;
    }

    @Transactional
    public void eliminar(Long id) {
        Producto producto = obtenerProductoService.obtener(id);
        boolean tieneUso = comprobarUsoProductoService.tieneUso(producto);

        if(producto.admiteConfigProduccion()) {
            eliminarConfigInyeccionService.eliminar(producto, tieneUso);
        }

        if(tieneUso) producto.desactivar();
        else productoRepository.delete(producto);
    }
}