package com.fabrica.stock.producto.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
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
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final EliminarConfigProduccionService eliminarConfigProduccionService;
    private final CrearConfigProduccionService crearConfigProduccionService;
    private final ValidarMaquinaParaProduccionService validarMaquinaParaProduccionService;

    public ConfigProduccionService(AgregarMateriaPrimaService agregarMateriaPrimaService,
                                   QuitarMateriaPrimaService quitarMateriaPrimaService,
                                   ObtenerProductoService obtenerProductoService,
                                   ObtenerConfigProduccionService obtenerConfigProduccionService,
                                   ObtenerMaquinaService obtenerMaquinaService,
                                   EliminarConfigProduccionService eliminarConfigProduccionService,
                                   CrearConfigProduccionService crearConfigProduccionService,
                                   ValidarMaquinaParaProduccionService validarMaquinaParaProduccionService)
    {
        this.agregarMateriaPrimaService = agregarMateriaPrimaService;
        this.quitarMateriaPrimaService = quitarMateriaPrimaService;
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.eliminarConfigProduccionService = eliminarConfigProduccionService;
        this.crearConfigProduccionService = crearConfigProduccionService;
        this.validarMaquinaParaProduccionService = validarMaquinaParaProduccionService;
    }

    public void validarMaquinaParaProduccion(Long maquinaId) {
        validarMaquinaParaProduccionService.validar(obtenerMaquinaService.obtener(maquinaId));
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