package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.maquina.api.dto.request.IdPaletMateriaPrimaRequest;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ConsumirMateriaPrimaService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerPaletService obtenerPaletService;
    private final CrearMovimientoService crearMovimientoService;

    public ConsumirMateriaPrimaService(ObtenerMaquinaService obtenerMaquinaService,
                                       ObtenerPaletService obtenerPaletService,
                                       CrearMovimientoService crearMovimientoService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerPaletService = obtenerPaletService;
        this.crearMovimientoService = crearMovimientoService;
    }

    @Transactional
    public void consumir(Long maquinaId, IdPaletMateriaPrimaRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Palet paletMp = obtenerPaletService.obtener(request.paletMateriaPrimaId());

        if(maquina.noTengoConfigProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(maquina.estaEnProduccion() && maquina.getPalet().soyTipoGranel())
            throw new RecursoNoValidoException("Servicio no disponible.");

        else if(maquina.estaEnProduccion() && maquina.getPalet().estoyAbierto())
            throw new EstadoNoValidoException( "Servicio no disponible con palet abierto.");

        if(!maquina.getConfigProduccion().contieneMateriaPrima(paletMp))
            throw new RecursoNoEncontradoException("Materia prima no está en la lista.");

        crearMovimientoService.consumicion(paletMp);
        maquina.consumirMateriaPrima(paletMp);
    }
}