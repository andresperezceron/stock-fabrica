package com.fabrica.stock.estacion.application;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.estacion.domain.exceptions.NombreEstacionDuplicadoException;
import com.fabrica.stock.estacion.infrastructure.EstacionRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearEstacionSiNoExisteService {
    private final EstacionRepository estacionRepository;

    public CrearEstacionSiNoExisteService(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    public Estacion crear(String nombre) {
        if(estacionRepository.existsByNombre(nombre)) {
            return estacionRepository.save(new Estacion(nombre));
        } else throw new NombreEstacionDuplicadoException("Ya existe una estación con el nombre: " + nombre);
    }
}