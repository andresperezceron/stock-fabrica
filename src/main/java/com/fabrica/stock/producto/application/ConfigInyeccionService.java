package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.configinyeccion.application.ActualizarObservacionesService;
import com.fabrica.stock.producto.configinyeccion.application.AgregarMateriaPrimaService;
import com.fabrica.stock.producto.configinyeccion.application.QuitarMateriaPrimaService;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ConfigInyeccionService {
    private final AgregarMateriaPrimaService agregarMateriaPrimaService;
    private final QuitarMateriaPrimaService quitarMateriaPrimaService;
    private final ObtenerProductoService obtenerProductoService;
    private final ActualizarObservacionesService actualizarObservacionesService;

    public ConfigInyeccionService(AgregarMateriaPrimaService agregarMateriaPrimaService,
                                  QuitarMateriaPrimaService quitarMateriaPrimaService,
                                  ObtenerProductoService obtenerProductoService,
                                  ActualizarObservacionesService actualizarObservacionesService)
    {
        this.agregarMateriaPrimaService = agregarMateriaPrimaService;
        this.quitarMateriaPrimaService = quitarMateriaPrimaService;
        this.obtenerProductoService = obtenerProductoService;
        this.actualizarObservacionesService = actualizarObservacionesService;
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

    public void actualizarObservaciones(Long productoId, String observaciones) {
        Producto producto = obtenerProductoService.obtener(productoId);
        actualizarObservacionesService.actualizar(producto, observaciones);
    }
}