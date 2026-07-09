package com.fabrica.stock.configurador.application;

import com.fabrica.stock.configurador.api.dto.FinalizarPaletRequest;
import com.fabrica.stock.configurador.api.dto.NuevoPaletRequest;
import com.fabrica.stock.configurador.api.dto.NuevoPaletResponse;
import com.fabrica.stock.contenidopalet.applicantion.CrearContenidoService;
import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.lote.application.CrearLoteService;
import com.fabrica.stock.lote.application.ObtenerLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.movimiento.application.CrearPrimerMovimientoService;
import com.fabrica.stock.palet.application.*;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.configurador.api.dto.CerrarPaletRequest;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConfiguradorPaletService {
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerPaletService obtenerPaletService;
    private final CrearPaletService crearPaletService;
    private final CrearLoteService crearLoteService;
    private final CrearPrimerMovimientoService crearPrimerMovimientoService;
    private final CrearContenidoService crearContenidoService;
    private final AbrirPaletService abrirPaletService;
    private final CerrarPaletService cerrarPaletService;
    private final FinalizarPaletService finalizarPaletService;

    public ConfiguradorPaletService(ObtenerUbicacionService obtenerUbicacionService,
                                    ObtenerProductoService obtenerProductoService,
                                    ObtenerLoteService obtenerLoteService,
                                    ObtenerPaletService obtenerPaletService,
                                    CrearPaletService crearPaletService,
                                    CrearLoteService crearLoteService,
                                    CrearPrimerMovimientoService crearPrimerMovimientoService,
                                    CrearContenidoService crearContenidoService,
                                    AbrirPaletService abrirPaletService,
                                    CerrarPaletService cerrarPaletService,
                                    FinalizarPaletService finalizarPaletService)
    {
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.crearPaletService = crearPaletService;
        this.crearPrimerMovimientoService = crearPrimerMovimientoService;
        this.abrirPaletService = abrirPaletService;
        this.obtenerPaletService = obtenerPaletService;
        this.cerrarPaletService = cerrarPaletService;
        this.obtenerProductoService = obtenerProductoService;
        this.crearLoteService = crearLoteService;
        this.crearContenidoService = crearContenidoService;
        this.finalizarPaletService = finalizarPaletService;
    }

    public NuevoPaletResponse nuevoPalet(NuevoPaletRequest request) {
        Ubicacion ubicacion = obtenerUbicacionService.obtener(request.ubicacionId);
        Palet palet = crearPaletService.crear(ubicacion);
        return new NuevoPaletResponse(palet.getId());
    }

    public void abrirPalet(Long idPalet) {
        abrirPaletService.abrir(obtenerPaletService.obtener(idPalet));
    }

    @Transactional
    public void cerrarPalet(CerrarPaletRequest request) {
        Palet palet = obtenerPaletService.obtener(request.paletId);
        cerrarPaletService.cerrar(palet);
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

    @Transactional
    public void finalizarPalet(FinalizarPaletRequest request) {
        Palet palet = obtenerPaletService.obtener(request.paletId);
        finalizarPaletService.finalizar(palet);
        LocalDateTime fecha = LocalDateTime.now();

        for(ContenidoPalet contenido : palet.getContenidos()) {
            crearPrimerMovimientoService.crear(
                    contenido.getProducto(),
                    contenido.getLote(),
                    contenido.getCantidad(),
                    fecha);
        }
    }
}