package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.api.dto.MaquinaIdPaletIdRequest;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FinalizarPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ComprobarFinalizarPaletService comprobarFinalizarPaletService;
    private final PaletRepository paletRepository;
    private final CrearMovimientoService crearMovimientoService;

    public FinalizarPaletService(ObtenerPaletService obtenerPaletService,
                                 ObtenerMaquinaService obtenerMaquinaService,
                                 ComprobarFinalizarPaletService comprobarFinalizarPaletService,
                                 PaletRepository paletRepository,
                                 CrearMovimientoService crearMovimientoService)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.comprobarFinalizarPaletService = comprobarFinalizarPaletService;
        this.paletRepository = paletRepository;
        this.crearMovimientoService = crearMovimientoService;
    }

    @Transactional
    public void finalizar(MaquinaIdPaletIdRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(request.maquinaId());
        Palet palet = obtenerPaletService.obtener(request.paletId());

        comprobarFinalizarPaletService.comprobar(maquina, palet);

        if(!palet.estoyVacio()) {
            palet.consolidarContenidos();
            crearMovimientoService.primerMovimiento(palet);
            palet.pendienteValidacion();
            palet.getMaquina().configurada();
        }else paletRepository.delete(palet);
    }
}