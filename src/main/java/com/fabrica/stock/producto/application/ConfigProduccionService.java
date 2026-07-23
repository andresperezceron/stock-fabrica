package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.api.dto.ActualizarObservacionesRequest;
import com.fabrica.stock.producto.configproduccion.application.ActualizarObservacionesService;
import com.fabrica.stock.producto.configproduccion.application.AgregarMateriaPrimaService;
import com.fabrica.stock.producto.configproduccion.application.CrearConfigProduccionService;
import com.fabrica.stock.producto.configproduccion.application.QuitarMateriaPrimaService;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ConfigProduccionService {
    private final AgregarMateriaPrimaService agregarMateriaPrimaService;
    private final QuitarMateriaPrimaService quitarMateriaPrimaService;
    private final ObtenerProductoService obtenerProductoService;
    private final ActualizarObservacionesService actualizarObservacionesService;
    private final CrearConfigProduccionService crearConfigProduccionService;

    public ConfigProduccionService(AgregarMateriaPrimaService agregarMateriaPrimaService,
                                   QuitarMateriaPrimaService quitarMateriaPrimaService,
                                   ObtenerProductoService obtenerProductoService,
                                   ActualizarObservacionesService actualizarObservacionesService,
                                   CrearConfigProduccionService crearConfigProduccionService)
    {
        this.agregarMateriaPrimaService = agregarMateriaPrimaService;
        this.quitarMateriaPrimaService = quitarMateriaPrimaService;
        this.obtenerProductoService = obtenerProductoService;
        this.actualizarObservacionesService = actualizarObservacionesService;
        this.crearConfigProduccionService = crearConfigProduccionService;
    }

    public void crearConfigProduccion(Long productoId) {
        Producto producto = obtenerProductoService.obtener(productoId);
        crearConfigProduccionService.crear(producto);
    }

    public void agregarMateriaPrima(Long productoId, Long materiaPrimaId) {
        Producto producto = obtenerProductoService.obtener(productoId);
        Producto materiaPrima = obtenerProductoService.obtener(materiaPrimaId);
        agregarMateriaPrimaService.agregar(producto, materiaPrima);
    }

    public void quitarMateriaPrima(Long productoId, Long materiaPrimaId) {
        Producto producto = obtenerProductoService.obtener(productoId);
        Producto materiaPrima = obtenerProductoService.obtener(materiaPrimaId);
        quitarMateriaPrimaService.quitar(producto, materiaPrima);
    }

    public void actualizarObservaciones(Long productoId, ActualizarObservacionesRequest request) {
        Producto producto = obtenerProductoService.obtener(productoId);
        actualizarObservacionesService.actualizar(producto, request.observaciones());
    }
}