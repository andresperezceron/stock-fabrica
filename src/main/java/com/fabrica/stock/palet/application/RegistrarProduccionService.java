package com.fabrica.stock.palet.application;

import com.fabrica.stock.configproduccion.application.ObtenerConfigProduccionService;
import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.contenidopalet.infrastructure.ContenidoPaletRepository;
import com.fabrica.stock.lote.application.CrearLoteSiNoExisteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.api.dto.RegistrarProduccionRequest;
import com.fabrica.stock.palet.domain.Palet;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RegistrarProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerPaletService obtenerPaletService;
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ComprobarRegProduccionService comprobarRegProduccionService;
    private final CrearLoteSiNoExisteService crearLoteSiNoExisteService;
    private final ContenidoPaletRepository contenidoPaletRepository;

    public RegistrarProduccionService(ObtenerMaquinaService obtenerMaquinaService,
                                      ObtenerPaletService obtenerPaletService,
                                      ObtenerConfigProduccionService obtenerConfigProduccionService,
                                      ComprobarRegProduccionService comprobarRegProduccionService,
                                      CrearLoteSiNoExisteService crearLoteSiNoExisteService,
                                      ContenidoPaletRepository contenidoPaletRepository)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerPaletService = obtenerPaletService;
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.comprobarRegProduccionService = comprobarRegProduccionService;
        this.crearLoteSiNoExisteService = crearLoteSiNoExisteService;
        this.contenidoPaletRepository = contenidoPaletRepository;
    }

    @Transactional
    public void registrar(RegistrarProduccionRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(request.maquinaId());
        Palet palet = obtenerPaletService.obtener(request.paletId());
        ConfigProduccion config = obtenerConfigProduccionService.porProducto(maquina.getProductoActivo());

        comprobarRegProduccionService.comprobar(maquina, palet, config, request);

        Lote lote = crearLoteSiNoExisteService.crearSiNoExiste(
                OrigenLote.PRODUCCION,
                null,
                maquina,
                maquina.getProductoActivo());

        contenidoPaletRepository.save(new ContenidoPalet(
                palet,
                lote,
                request.tipo(),
                request.cantidad(),
                config.obtenerLotesMateriaPrima()
        ));

        palet.cerrar();
    }
}