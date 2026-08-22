package com.fabrica.stock.entradaforanea.application;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.contenidopalet.domain.TipoContenido;
import com.fabrica.stock.contenidopalet.infrastructure.ContenidoPaletRepository;
import com.fabrica.stock.entradaforanea.api.dto.CrearPaletGranelRequest;
import com.fabrica.stock.estacion.application.ObtenerEstacionService;
import com.fabrica.stock.lote.application.CrearLoteSiNoExisteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.TipoPalet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CrearPaletGranelService {
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerEstacionService obtenerEstacionService;
    private final CrearLoteSiNoExisteService crearLoteSiNoExisteService;
    private final CrearMovimientoService crearMovimientoService;
    private final ContenidoPaletRepository contenidoPaletRepository;
    private final PaletRepository paletRepository;

    public CrearPaletGranelService(ObtenerProductoService obtenerProductoService,
                                   ObtenerEstacionService obtenerEstacionService,
                                   CrearLoteSiNoExisteService crearLoteSiNoExisteService,
                                   CrearMovimientoService crearMovimientoService,
                                   ContenidoPaletRepository contenidoPaletRepository,
                                   PaletRepository paletRepository)
    {
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerEstacionService = obtenerEstacionService;
        this.crearLoteSiNoExisteService = crearLoteSiNoExisteService;
        this.crearMovimientoService = crearMovimientoService;
        this.contenidoPaletRepository = contenidoPaletRepository;
        this.paletRepository = paletRepository;
    }

    @Transactional
    public void crear(CrearPaletGranelRequest request) {
        Producto producto = obtenerProductoService.obtener(request.productoId());
        String loteProvNormalizado = request.loteProveedor().trim();

        Lote lote = crearLoteSiNoExisteService.crearSiNoExiste(
                loteProvNormalizado,
                null,
                producto);

        Palet palet = paletRepository.save(new Palet(
                TipoPalet.GRANEL,
                producto,
                obtenerEstacionService.obtenerProveedor()));

        contenidoPaletRepository.save(new ContenidoPalet(
                palet,
                lote,
                TipoContenido.COMPLETA,
                request.cantidad(),
                new ArrayList<Lote>()
        ));

        crearMovimientoService.entradaForanea(palet);
    }
}