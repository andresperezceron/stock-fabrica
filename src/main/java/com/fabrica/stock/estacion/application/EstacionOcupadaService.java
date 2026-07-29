package com.fabrica.stock.estacion.application;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import org.springframework.stereotype.Service;

@Service
public class EstacionOcupadaService {
    private final PaletRepository paletRepository;

    public EstacionOcupadaService(PaletRepository paletRepository) {
        this.paletRepository = paletRepository;
    }

    public boolean estaOcupada(Estacion estacion) {
        return paletRepository.existsByLocalizacion(estacion);
    }
}