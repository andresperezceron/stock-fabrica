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

    public Estacion obtenerProduccion() { return obtener(Estaciones.PRODUCCION); }

    public Estacion obtenerValidacion() { return obtener(Estaciones.VALIDACION); }

    public Estacion obtenerExpedicion() { return obtener(Estaciones.EXPEDICION); }

    public Estacion obtenerProveedor() { return obtener(Estaciones.PROVEEDOR); }

    public Estacion obtenerCorreccion() { return obtener(Estaciones.CORRECCION); }

    private Estacion obtener(String nombre) {
        return estacionRepository.findByNombre(nombre)
                .orElseThrow(() -> new EstacionNoEncontradaException("Estación no encontrada: " + nombre));
    }
}