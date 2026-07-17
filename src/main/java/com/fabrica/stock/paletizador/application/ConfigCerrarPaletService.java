package com.fabrica.stock.paletizador.application;

import com.fabrica.stock.paletizador.api.dto.CerrarPaletRequest;
import com.fabrica.stock.contenidopalet.applicantion.CrearContenidoService;
import com.fabrica.stock.lote.application.CrearLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.palet.application.CambiarEstadoPaletService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConfigCerrarPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final ObtenerProductoService obtenerProductoService;
    private final CrearLoteService crearLoteService;
    private final CrearContenidoService crearContenidoService;
    private final CambiarEstadoPaletService cambiarEstadoPaletService;


    public ConfigCerrarPaletService(ObtenerPaletService obtenerPaletService,
                                    ObtenerProductoService obtenerProductoService,
                                    CrearLoteService crearLoteService,
                                    CrearContenidoService crearContenidoService,
                                    CambiarEstadoPaletService cambiarEstadoPaletService)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.obtenerProductoService = obtenerProductoService;
        this.crearLoteService = crearLoteService;
        this.crearContenidoService = crearContenidoService;
        this.cambiarEstadoPaletService = cambiarEstadoPaletService;
    }

    @Transactional
    public void cerrarPalet(CerrarPaletRequest request) {
        Palet palet = obtenerPaletService.obtener(request.paletId);
        cambiarEstadoPaletService.cerrar(palet);
        LocalDateTime fecha = LocalDateTime.now();
        Producto producto = obtenerProductoService.obtener(request.productoId);

        Lote lote = crearLoteService.crear(
                OrigenLote.PRODUCCION,
                null,
                request.maquina,
                fecha
        );

        crearContenidoService.crear(palet, producto, lote, request.cantidad, fecha);
    }
}