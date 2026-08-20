package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FinalizarProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final PaletRepository paletRepository;
    private final CrearMovimientoService crearMovimientoService;

    public FinalizarProduccionService(ObtenerMaquinaService obtenerMaquinaService,
                                      PaletRepository paletRepository,
                                      CrearMovimientoService crearMovimientoService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.paletRepository = paletRepository;
        this.crearMovimientoService = crearMovimientoService;
    }

    @Transactional
    public void finalizar(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Palet palet = maquina.getPalet();

        if(!maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(palet.estoyAbierto())
            throw new EstadoNoValidoException("Palet no está cerrado.");

        if(!palet.estoyVacio()) {
            palet.consolidarContenidos();
            crearMovimientoService.primerMovimiento(palet);
        }else paletRepository.delete(palet);

        maquina.quitarPalet();
        maquina.configurada();
    }
}