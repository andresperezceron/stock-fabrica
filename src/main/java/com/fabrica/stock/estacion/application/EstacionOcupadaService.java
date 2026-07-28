package com.fabrica.stock.estacion.application;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.estacion.infrastructure.EstacionRepository;
import org.springframework.stereotype.Service;

@Service
public class EstacionOcupadaService {
    private final EstacionRepository estacionRepository;

    public EstacionOcupadaService(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    public boolean estaOcupda(Estacion estacion) {
        //return estacionRepository.existsByLocalizacion(estacion);
        //return false;
        return false;
    }
}