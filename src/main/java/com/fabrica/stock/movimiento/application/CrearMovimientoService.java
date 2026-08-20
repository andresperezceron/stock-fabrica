package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.estacion.application.ObtenerEstacionService;
import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.movimiento.domain.Movimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoRepository;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.stereotype.Service;

@Service
public class CrearMovimientoService {
    private final ObtenerEstacionService obtenerEstacionService;
    private final MovimientoRepository movimientoRepository;

    public CrearMovimientoService(ObtenerEstacionService obtenerEstacionService,
                                  MovimientoRepository movimientoRepository)
    {
        this.obtenerEstacionService = obtenerEstacionService;
        this.movimientoRepository = movimientoRepository;
    }

    public void primerMovimiento(Palet palet) {
        if(!(palet.getLocalizacion() instanceof Estacion produccion))
            throw new RecursoNoEncontradoException("El palet no está en estación Producción.");

        Estacion validacion = obtenerEstacionService.obtenerValidacion();
        movimientoRepository.saveAll(Movimiento.primerMovimiento(palet, produccion, validacion));
        palet.moverA(validacion);
        palet.pendienteValidacion();
    }

    public void paraConsumo(Palet palet) {
        if(!(palet.getLocalizacion() instanceof Ubicacion ubicacion))
            throw new RecursoNoEncontradoException("El palet no está en una ubicación.");

        Estacion produccion = obtenerEstacionService.obtenerProduccion();
        movimientoRepository.saveAll(Movimiento.paraConsumo(palet, ubicacion, produccion));
        palet.moverA(produccion);
        palet.enConsumo();
    }

    public void consumicion(Palet palet) {
        if(!(palet.getLocalizacion() instanceof Estacion produccion))
            throw new RecursoNoEncontradoException("El palet no está en Producción.");

        Estacion consumicion = obtenerEstacionService.obtenerConsumicion();
        movimientoRepository.saveAll(Movimiento.consumicion(palet, produccion, consumicion));
        palet.moverA(consumicion);
        palet.consumido();
    }
}