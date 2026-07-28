package com.fabrica.stock.familia.application;

import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.familia.domain.exceptions.FamiliaNoEncontradaException;
import com.fabrica.stock.familia.infrastructure.FamiliaRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerFamiliaService {
    private final FamiliaRepository familiaRepository;

    public ObtenerFamiliaService(FamiliaRepository familiaRepository) {
        this.familiaRepository = familiaRepository;
    }

    public Familia obtener(Long id) {
        return familiaRepository.findById(id).orElseThrow(() ->
                new FamiliaNoEncontradaException("No existe familia con maquinaId " + id));
    }
}