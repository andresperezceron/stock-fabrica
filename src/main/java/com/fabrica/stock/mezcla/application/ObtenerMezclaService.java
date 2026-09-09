package com.fabrica.stock.mezcla.application;

import com.fabrica.stock.mezcla.domain.Mezcla;
import com.fabrica.stock.mezcla.infrastructure.MezclaRepository;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class ObtenerMezclaService {
    private final MezclaRepository mezclaRepository;

    public ObtenerMezclaService(MezclaRepository mezclaRepository) {
        this.mezclaRepository = mezclaRepository;
    }

    public Mezcla obtener(Long mezclaId) {
        return mezclaRepository.findById(mezclaId).orElseThrow(() ->
                new RecursoNoEncontradoException("Mezcla no encontrada."));
    }
}