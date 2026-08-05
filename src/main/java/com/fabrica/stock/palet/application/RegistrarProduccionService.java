package com.fabrica.stock.palet.application;

import com.fabrica.stock.contenidopalet.infrastructure.ContenidoPaletRepository;
import com.fabrica.stock.lote.application.CrearLoteSiNoExisteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.exceptions.EstadoMaquinaNoValidoException;
import com.fabrica.stock.palet.api.dto.RegistrarProduccionRequest;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RegistrarProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerPaletService obtenerPaletService;
    private final CrearLoteSiNoExisteService crearLoteSiNoExisteService;
    private final ContenidoPaletRepository contenidoPaletRepository;

    public RegistrarProduccionService(ObtenerMaquinaService obtenerMaquinaService,
                                      ObtenerPaletService obtenerPaletService,
                                      CrearLoteSiNoExisteService crearLoteSiNoExisteService,
                                      ContenidoPaletRepository contenidoPaletRepository)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerPaletService = obtenerPaletService;
        this.crearLoteSiNoExisteService = crearLoteSiNoExisteService;
        this.contenidoPaletRepository = contenidoPaletRepository;
    }

    @Transactional
    public void registrar(RegistrarProduccionRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(request.maquinaId());
        Palet palet = obtenerPaletService.obtener(request.paletId());

        if(!maquina.estaProduccion()) {
            throw new EstadoMaquinaNoValidoException("No se puede registrar producción con la maquina en estado: " +
                    maquina.getEstado());
        }else if(palet.estaCerrado()) throw new EstadoPaletInvalidoException("El palet está cerrado.");

        Lote lote = crearLoteSiNoExisteService.crearSiNoExiste(
                OrigenLote.PRODUCCION,
                null,
                maquina,
                maquina.getProductoActivo());

        contenidoPaletRepository.save(palet.registrarContenido(lote, request.cantidad()));
        palet.cerrar();
    }
}