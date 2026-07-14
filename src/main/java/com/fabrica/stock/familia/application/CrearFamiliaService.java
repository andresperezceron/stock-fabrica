package com.fabrica.stock.familia.application;

import com.fabrica.stock.familia.api.dto.CrearFamiliaRequest;
import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.familia.domain.validation.CrearFamiliaValidator;
import com.fabrica.stock.familia.infrastructure.FamiliaRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearFamiliaService {
    private final FamiliaRepository familiaRepository;

    public CrearFamiliaService(FamiliaRepository familiaRepository) {
        this.familiaRepository = familiaRepository;
    }

    public void crear(CrearFamiliaRequest request){
        new CrearFamiliaValidator(request, familiaRepository).validate();
        familiaRepository.save(new Familia(request.nombre));
    }
}