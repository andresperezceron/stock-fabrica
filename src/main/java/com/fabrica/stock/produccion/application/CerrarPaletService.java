package com.fabrica.stock.produccion.application;

import com.fabrica.stock.contenidopalet.applicantion.CrearContenidoService;
import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.lote.application.CrearLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.movimiento.application.PrimerMovimientoService;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.produccion.api.dto.CerrarPaletRequest;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CerrarPaletService {
    private final CrearLoteService crearLoteService;
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerPaletService obtenerPaletService;
    private final CrearContenidoService crearContenidoService;

    public CerrarPaletService(
            CrearLoteService crearLoteService,
            ObtenerProductoService obtenerProductoService,
            ObtenerPaletService obtenerPaletService,
            CrearContenidoService crearContenidoService)
    {
        this.crearLoteService = crearLoteService;
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerPaletService = obtenerPaletService;
        this.crearContenidoService = crearContenidoService;
    }

    public void cerrarPalet(CerrarPaletRequest request) {
        Lote lote = crearLoteService.crear(
                obtenerProductoService.obtener(request.productoId),
                OrigenLote.PRODUCCION,
                null,
                MaquinaProduccion.valueOf(request.maquina),
                LocalDateTime.now());
        crearContenidoService.crear(request.paletId, lote.getId(), request.cantidad);
    }
}
