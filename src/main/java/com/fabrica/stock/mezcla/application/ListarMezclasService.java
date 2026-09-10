package com.fabrica.stock.mezcla.application;

import com.fabrica.stock.mezcla.api.dto.MezclasResponse;
import com.fabrica.stock.mezcla.infrastructure.MezclaRepository;
import org.springframework.stereotype.Service;

@Service
public class ListarMezclasService {
    private final  MezclaRepository mezclaRepository;

    public ListarMezclasService(MezclaRepository mezclaRepository) {
        this.mezclaRepository = mezclaRepository;
    }

    public MezclasResponse listar() {
        return MezclasResponse.of(this.mezclaRepository.findAll());
    }
}