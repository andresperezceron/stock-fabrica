package com.fabrica.stock.maquina.application;

import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.contenidopalet.domain.TipoContenido;
import com.fabrica.stock.contenidopalet.infrastructure.ContenidoPaletRepository;
import com.fabrica.stock.lote.application.CrearLoteSiNoExisteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.api.dto.request.RegistrarProduccionRequest;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RegistrarProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final CrearLoteSiNoExisteService crearLoteSiNoExisteService;
    private final ContenidoPaletRepository contenidoPaletRepository;

    public RegistrarProduccionService(ObtenerMaquinaService obtenerMaquinaService,
                                      CrearLoteSiNoExisteService crearLoteSiNoExisteService,
                                      ContenidoPaletRepository contenidoPaletRepository)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.crearLoteSiNoExisteService = crearLoteSiNoExisteService;
        this.contenidoPaletRepository = contenidoPaletRepository;
    }

    @Transactional
    public void registrar(Long maquinaId, RegistrarProduccionRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Palet palet = maquina.getPalet();
        ConfigProduccion config = maquina.getConfigProduccion();
        TipoContenido tipo = request.tipoContenido();
        Integer cantidad = request.cantidad();

        if(!maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(palet.estoyCerrado())
            throw new EstadoNoValidoException("Palet está cerrado.");

        if((tipo == TipoContenido.GRANEL || tipo == TipoContenido.PERSONALIZADA) && cantidad != 1)
            throw new RecursoNoValidoException("Solo una pieza por registro.");

        if(tipo == TipoContenido.PERSONALIZADA && palet.tengoPersonalizada())
            throw new ConflictException("Palet ya tiene caja personalizada.");

        if(palet.registroSuperaCapacidad(tipo, cantidad, config))
            throw new RecursoNoValidoException("Registro supera cajas por palet.");

        Lote lote = crearLoteSiNoExisteService.crearSiNoExiste(
                OrigenLote.PRODUCCION,
                null,
                maquina,
                config.getProducto());

        contenidoPaletRepository.save(new ContenidoPalet(
                palet,
                lote,
                tipo,
                cantidad,
                config.obtenerLotesMateriaPrima()
        ));

        palet.cerrar();
    }
}