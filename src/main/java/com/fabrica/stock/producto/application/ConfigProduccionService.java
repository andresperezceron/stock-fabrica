package com.fabrica.stock.producto.application;

import com.fabrica.stock.configproduccion.application.*;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.producto.api.dto.ActualizarObservacionesRequest;
import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ConfigProduccionService {
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ObtenerPaletService obtenerPaletService;
    private final ActualizarCajasPorPaletService actualizarCajasPorPaletService;

    public ConfigProduccionService(ObtenerProductoService obtenerProductoService,
                                   ObtenerConfigProduccionService obtenerConfigProduccionService,
                                   ObtenerPaletService obtenerPaletService,
                                   ActualizarCajasPorPaletService actualizarCajasPorPaletService)
    {
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerPaletService = obtenerPaletService;
        this.actualizarCajasPorPaletService = actualizarCajasPorPaletService;
    }

    public void actualizarCajasPorPalet(Long paletId, Integer cajasPorPalet) {
        Palet palet = obtenerPaletService.obtener(paletId);
        actualizarCajasPorPaletService.actualizar(palet, cajasPorPalet);
    }

    public boolean validarConfigPorProducto(Producto producto) {
        return obtenerConfigProduccionService.porProducto(producto).esValida();
    }

    public void actualizarObservaciones(Long productoId, ActualizarObservacionesRequest request) {
        Producto producto = obtenerProductoService.obtener(productoId);
        obtenerConfigProduccionService
                .porProducto(producto)
                .actualizarObservaciones(request.observaciones());
    }
}