package com.fabrica.stock.estacion.application;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.estacion.domain.EstacionesSistema;
import com.fabrica.stock.estacion.domain.exceptions.EstacionNoEncontradaException;
import com.fabrica.stock.estacion.infrastructure.EstacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerEstacionService {
    private final EstacionRepository estacionRepository;

    public ObtenerEstacionService(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    public Estacion obtenerProduccion() { return obtener(EstacionesSistema.PRODUCCION); }

    public Estacion obtenerValidacion() { return obtener(EstacionesSistema.VALIDACION); }

    public Estacion obtenerExpedicion() { return obtener(EstacionesSistema.EXPEDICION); }

    public Estacion obtenerProveedor() { return obtener(EstacionesSistema.PROVEEDOR); }

    public Estacion obtenerCorreccion() { return obtener(EstacionesSistema.CORRECCION); }

    private Estacion obtener(String nombre) {
        return estacionRepository.findByNombre(nombre)
                .orElseThrow(() -> new EstacionNoEncontradaException("Estación no encontrada: " + nombre));
    }
}