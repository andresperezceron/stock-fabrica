package com.fabrica.stock.familia.application;

import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.familia.infrastructure.FamiliaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarFamiliaService {
    private final FamiliaRepository familiaRepository;

    public ListarFamiliaService(FamiliaRepository familiaRepository) {
        this.familiaRepository = familiaRepository;
    }

    public List<Familia> listar() {
        return familiaRepository.findAll();
    }
}
