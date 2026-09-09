package com.fabrica.stock.estacion.application;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.estacion.domain.Estaciones;
import com.fabrica.stock.estacion.infrastructure.EstacionRepository;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import org.springframework.stereotype.Service;

@Service
public class ObtenerEstacionService {
    private final EstacionRepository estacionRepository;

    public ObtenerEstacionService(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    public Estacion obtenerProduccion() { return obtenerPorNombre(NombreUbicacion.of(Estaciones.PRODUCCION)); }

    public Estacion obtenerValidacion() { return obtenerPorNombre(NombreUbicacion.of(Estaciones.VALIDACION)); }

    public Estacion obtenerExpedicion() { return obtenerPorNombre(NombreUbicacion.of(Estaciones.EXPEDICION)); }

    public Estacion obtenerProveedor() { return obtenerPorNombre(NombreUbicacion.of(Estaciones.PROVEEDOR)); }

    public Estacion obtenerCorreccion() { return obtenerPorNombre(NombreUbicacion.of(Estaciones.CORRECCION)); }

    public Estacion obtenerConsumicion() { return obtenerPorNombre(NombreUbicacion.of(Estaciones.CONSUMICION)); }

    public Estacion obtenerCosumoInyeccion() { return obtenerPorNombre(NombreUbicacion.of(Estaciones.CONSUMO_INYECCION)); }

    private Estacion obtenerPorNombre(NombreUbicacion nombre) {
        return estacionRepository.findByNombre(nombre)
                .orElseThrow(() -> new RecursoNoEncontradoException("Estación no encontrada: " + nombre));
    }
}