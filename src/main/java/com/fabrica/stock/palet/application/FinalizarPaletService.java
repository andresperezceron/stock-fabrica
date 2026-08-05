package com.fabrica.stock.palet.application;

import com.fabrica.stock.estacion.application.ObtenerEstacionService;
import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.movimiento.domain.Movimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoRepository;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FinalizarPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final ObtenerEstacionService obtenerEstacionService;
    private final PaletRepository paletRepository;
    private final MovimientoRepository movimientoRepository;

    public FinalizarPaletService(ObtenerPaletService obtenerPaletService,
                                 ObtenerEstacionService obtenerEstacionService,
                                 PaletRepository paletRepository,
                                 MovimientoRepository movimientoRepository)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.obtenerEstacionService = obtenerEstacionService;
        this.paletRepository = paletRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    public void finalizar(Long id) {
        Palet palet = obtenerPaletService.obtener(id);
        if(!palet.estaCerrado()) throw new EstadoPaletInvalidoException("El palet no está cerrado.");

        if(!palet.estaVacio()) {
            Estacion origen = obtenerEstacionService.obtenerProduccion();
            Estacion destino = obtenerEstacionService.obtenerValidacion();
            palet.finalizarProceso();
            palet.cambiarAPendienteValidacion();
            palet.moverA(destino);
            palet.getMaquina().cambiarAConfigurada();
            movimientoRepository.saveAll(Movimiento.primerMovimiento(palet, origen, destino));
        }else paletRepository.delete(palet);
    }
}