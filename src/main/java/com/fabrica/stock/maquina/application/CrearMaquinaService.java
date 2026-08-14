package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.api.dto.MaquinaRequest;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.vo.NombreMaquina;
import com.fabrica.stock.maquina.infrastructure.MaquinaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CrearMaquinaService {
    private final MaquinaRepository maquinaRepository;

    public CrearMaquinaService(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    @Transactional
    public void crear(MaquinaRequest request) {
        maquinaRepository.save(new Maquina(request.tipo(), NombreMaquina.of(request.nombre())));
    }
}