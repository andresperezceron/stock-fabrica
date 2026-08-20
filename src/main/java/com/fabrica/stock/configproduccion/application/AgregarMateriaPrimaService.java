package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.maquina.api.dto.request.IdPaletMateriaPrimaRequest;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AgregarMateriaPrimaService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerPaletService obtenerPaletService;
    private final CrearMovimientoService crearMovimientoService;

    public AgregarMateriaPrimaService(ObtenerMaquinaService obtenerMaquinaService,
                                      ObtenerPaletService obtenerPaletService,
                                      CrearMovimientoService crearMovimientoService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerPaletService = obtenerPaletService;
        this.crearMovimientoService = crearMovimientoService;
    }

    @Transactional
    public void agregar(Long maquinaId, IdPaletMateriaPrimaRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Palet paletMp = obtenerPaletService.obtener(request.paletMateriaPrimaId());

        if(maquina.noTengoConfigProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(!paletMp.soyConsumoInyeccion())
            throw new RecursoNoValidoException("Palet no valido.");

        if(maquina.estaEnProduccion() && maquina.getPalet().soyTipoGranel())
            throw new RecursoNoValidoException("Servicio no disponible.");

        else if(maquina.estaEnProduccion() && maquina.getPalet().estoyAbierto())
            throw new EstadoNoValidoException("Palet no esta cerrado.");

        if(maquina.getConfigProduccion().contieneMateriaPrima(paletMp))
            throw new ConflictException("Materia prima ya está agregada.");

        crearMovimientoService.paraConsumo(paletMp);
        maquina.agregarMateriaPrima(paletMp);
    }
}