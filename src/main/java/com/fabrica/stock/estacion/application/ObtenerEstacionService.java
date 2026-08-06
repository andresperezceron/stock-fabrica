package com.fabrica.stock.estacion.application;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.estacion.domain.Estaciones;
import com.fabrica.stock.estacion.domain.exceptions.EstacionNoEncontradaException;
import com.fabrica.stock.estacion.infrastructure.EstacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerEstacionService {
    private final EstacionRepository estacionRepository;

    public ObtenerEstacionService(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    public Estacion obtenerProduccion() { return obtenerPorNombre(Estaciones.PRODUCCION); }

    public Estacion obtenerValidacion() { return obtenerPorNombre(Estaciones.VALIDACION); }

    public Estacion obtenerExpedicion() { return obtenerPorNombre(Estaciones.EXPEDICION); }

    public Estacion obtenerProveedor() { return obtenerPorNombre(Estaciones.PROVEEDOR); }

    public Estacion obtenerCorreccion() { return obtenerPorNombre(Estaciones.CORRECCION); }

    private Estacion obtenerPorNombre(String nombre) {
        return estacionRepository.findByNombre(nombre)
                .orElseThrow(() -> new EstacionNoEncontradaException("Estación no encontrada: " + nombre));
    }
}