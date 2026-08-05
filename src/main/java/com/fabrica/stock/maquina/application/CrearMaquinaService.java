package com.fabrica.stock.maquina.application;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.estacion.infrastructure.EstacionRepository;
import com.fabrica.stock.maquina.api.dto.MaquinaRequest;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.vo.NombreMaquina;
import com.fabrica.stock.maquina.infrastructure.MaquinaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CrearMaquinaService {
    private final MaquinaRepository maquinaRepository;
    private final EstacionRepository estacionRepository;

    public CrearMaquinaService(MaquinaRepository maquinaRepository,
                               EstacionRepository estacionRepository) {
        this.maquinaRepository = maquinaRepository;
        this.estacionRepository = estacionRepository;
    }

    @Transactional
    public void crear(MaquinaRequest request) {
        Estacion estacion = estacionRepository.save(new Estacion("Maquina-" + request.nombre()));
        maquinaRepository.save(new Maquina(
                request.tipo(),
                NombreMaquina.of(request.nombre()),
                estacion
        ));
    }
}