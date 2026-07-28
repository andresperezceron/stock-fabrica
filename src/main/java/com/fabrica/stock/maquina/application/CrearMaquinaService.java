package com.fabrica.stock.maquina.application;

import com.fabrica.stock.estacion.application.CrearEstacionSiNoExisteService;
import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.maquina.api.dto.MaquinaRequest;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.vo.NombreMaquina;
import com.fabrica.stock.maquina.infrastructure.MaquinaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CrearMaquinaService {
    private final MaquinaRepository maquinaRepository;
    private final CrearEstacionSiNoExisteService crearEstacionSiNoExisteService;

    public CrearMaquinaService(MaquinaRepository maquinaRepository,
                               CrearEstacionSiNoExisteService crearEstacionSiNoExisteService) {
        this.maquinaRepository = maquinaRepository;
        this.crearEstacionSiNoExisteService = crearEstacionSiNoExisteService;
    }

    @Transactional
    public void crear(MaquinaRequest request) {
        Estacion estacionMaquina = crearEstacionSiNoExisteService.crear("Maquina-" + request.nombre());
        Maquina maquina = maquinaRepository.save(new Maquina(
                request.tipo(),
                NombreMaquina.of(request.nombre()),
                estacionMaquina
        ));
        crearEstacionSiNoExisteService.crear("Palet-" + maquina.getNombre());
    }
}