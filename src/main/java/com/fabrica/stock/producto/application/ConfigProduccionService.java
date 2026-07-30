package com.fabrica.stock.producto.application;

import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.producto.api.dto.ActualizarObservacionesRequest;
import com.fabrica.stock.producto.configproduccion.application.*;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ConfigProduccionService {
    private final AgregarMateriaPrimaService agregarMateriaPrimaService;
    private final QuitarMateriaPrimaService quitarMateriaPrimaService;
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ObtenerPaletService obtenerPaletService;
    private final EliminarConfigProduccionService eliminarConfigProduccionService;
    private final CrearConfigProduccionService crearConfigProduccionService;
    private final ActualizarCajasPorPaletService actualizarCajasPorPaletService;

    public ConfigProduccionService(AgregarMateriaPrimaService agregarMateriaPrimaService,
                                   QuitarMateriaPrimaService quitarMateriaPrimaService,
                                   ObtenerProductoService obtenerProductoService,
                                   ObtenerConfigProduccionService obtenerConfigProduccionService,
                                   ObtenerPaletService obtenerPaletService,
                                   EliminarConfigProduccionService eliminarConfigProduccionService,
                                   CrearConfigProduccionService crearConfigProduccionService,
                                   ActualizarCajasPorPaletService actualizarCajasPorPaletService)
    {
        this.agregarMateriaPrimaService = agregarMateriaPrimaService;
        this.quitarMateriaPrimaService = quitarMateriaPrimaService;
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerPaletService = obtenerPaletService;
        this.eliminarConfigProduccionService = eliminarConfigProduccionService;
        this.crearConfigProduccionService = crearConfigProduccionService;
        this.actualizarCajasPorPaletService = actualizarCajasPorPaletService;
    }

    public void actualizarCajasPorPalet(Long paletId, Integer cajasPorPalet) {
        Palet palet = obtenerPaletService.obtener(paletId);
        actualizarCajasPorPaletService.actualizar(palet, cajasPorPalet);
    }

    public boolean esValida(Producto producto) {
        return obtenerConfigProduccionService.obtenerPorProducto(producto).esValida();
    }

    public void crearSinoExiste(Producto producto) {
        crearConfigProduccionService.crearPorProducto(producto);
    }

    public void eliminarPorProducto(Producto producto,  boolean productoTieneUso) {
        eliminarConfigProduccionService.eliminarPorProducto(producto, productoTieneUso);
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
        obtenerConfigProduccionService
                .obtenerPorProducto(producto)
                .actualizarObservaciones(request.observaciones());
    }
}