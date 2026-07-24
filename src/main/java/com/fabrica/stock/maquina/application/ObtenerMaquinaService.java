package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.exceptions.MaquinaNoEncontradaException;
import com.fabrica.stock.maquina.infrastructure.MaquinaRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerMaquinaService {
    private final MaquinaRepository maquinaRepository;

    public ObtenerMaquinaService(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    public Maquina obtener(Long id) {
        return maquinaRepository.findById(id).orElseThrow(() ->
                new MaquinaNoEncontradaException("Maquina no encontrada."));
    }
}