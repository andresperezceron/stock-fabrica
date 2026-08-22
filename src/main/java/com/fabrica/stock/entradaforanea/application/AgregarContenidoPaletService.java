package com.fabrica.stock.entradaforanea.application;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.contenidopalet.domain.TipoContenido;
import com.fabrica.stock.contenidopalet.infrastructure.ContenidoPaletRepository;
import com.fabrica.stock.entradaforanea.api.dto.AgregarContenidoRequest;
import com.fabrica.stock.lote.application.CrearLoteSiNoExisteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AgregarContenidoPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final CrearLoteSiNoExisteService crearLoteSiNoExisteService;
    private final ContenidoPaletRepository contenidoPaletRepository;

    public AgregarContenidoPaletService(ObtenerPaletService obtenerPaletService,
                                        CrearLoteSiNoExisteService crearLoteSiNoExisteService,
                                        ContenidoPaletRepository contenidoPaletRepository)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.crearLoteSiNoExisteService = crearLoteSiNoExisteService;
        this.contenidoPaletRepository = contenidoPaletRepository;
    }

    @Transactional
    public void agregar(AgregarContenidoRequest request) {
        Palet palet = obtenerPaletService.obtener(request.paletId());
        String loteProvNormalizado = request.loteProveedor().trim();

        if(!palet.estoyEstacionProveedor())
            throw new ConflictException("El palet no está en Proveedor.");

        Lote lote = crearLoteSiNoExisteService.crearSiNoExiste(
                loteProvNormalizado,
                null,
                palet.getProducto());

        contenidoPaletRepository.save(new ContenidoPalet(
                palet,
                lote,
                TipoContenido.COMPLETA,
                request.cantidad(),
                new ArrayList<Lote>()
        ));
    }
}