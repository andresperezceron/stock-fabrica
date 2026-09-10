package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.estacion.application.ObtenerEstacionService;
import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.movimiento.domain.Movimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoRepository;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
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
        if(!(palet.getLocalizacion() instanceof Estacion estacion))
            throw new RecursoNoEncontradoException("El palet no está en estación Producción.");

        Estacion produccion = obtenerEstacionService.obtenerProduccion();
        Estacion validacion = obtenerEstacionService.obtenerValidacion();

        if(!estacion.getNombre().equals(produccion.getNombre()))
            throw new ConflictException("El palet no está en producción.");

        movimientoRepository.saveAll(Movimiento.primerMovimiento(palet, estacion, validacion));
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
        if(!(palet.getLocalizacion() instanceof Estacion estacion))
            throw new RecursoNoEncontradoException("El palet no está en una estación.");

        Estacion produccion = obtenerEstacionService.obtenerProduccion();
        Estacion consumicion = obtenerEstacionService.obtenerConsumicion();

        if(!estacion.getNombre().equals(produccion.getNombre()))
            throw new ConflictException("El palet no está en producción.");

        movimientoRepository.saveAll(Movimiento.consumicion(palet, estacion, consumicion));
        palet.moverA(consumicion);
        palet.consumido();
    }

    public void consumoInyeccion(Palet palet) {
        if(!(palet.getLocalizacion() instanceof Ubicacion ubicacion))
            throw new RecursoNoEncontradoException("El palet no está en una ubicación.");

        Estacion consumoInyeccion = obtenerEstacionService.obtenerCosumoInyeccion();

        movimientoRepository.saveAll(Movimiento.consumoInyeccion(palet, ubicacion, consumoInyeccion));
        palet.moverA(consumoInyeccion);
    }

    public void entradaForanea(Palet palet) {
        if(!palet.estoyEstacionProveedor())
            throw new ConflictException("Palet no está en Proveedor.");

        Estacion validacion = obtenerEstacionService.obtenerValidacion();

        movimientoRepository.saveAll(Movimiento.entradaForanea(palet, palet.getEstacion(), validacion));
        palet.moverA(validacion);
        palet.pendienteValidacion();
    }

    public void validacion(Palet palet, Ubicacion ubicacion) {
        if(!palet.estoyEstacionValidacion()) throw new ConflictException("Palet no está en validación.");
        if(ubicacion == null) throw new RecursoNoEncontradoException("Ubicación no encontrada.");

        movimientoRepository.saveAll(Movimiento.validacion(palet, palet.getEstacion(), ubicacion));
        palet.moverA(ubicacion);
        palet.validado();
    }

    public void devolucionConsumoInyeccion(Palet palet) {
        if(!palet.estoyEstacionConsumoInyeccion()) throw new ConflictException("Palet no está en consumo inyección.");
        Estacion validacion = obtenerEstacionService.obtenerValidacion();
        movimientoRepository.saveAll(Movimiento.devolucionConsumoInyeccion(palet,  palet.getEstacion(), validacion));

        palet.moverA(validacion);
        palet.pendienteValidacion();
    }
}