package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.api.dto.MaquinaRequest;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.vo.NombreMaquina;
import com.fabrica.stock.maquina.infrastructure.MaquinaRepository;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.stereotype.Service;

@Service
public class CrearMaquinaService {
    private final MaquinaRepository maquinaRepository;
    private final ObtenerUbicacionService obtenerUbicacionService;

    public CrearMaquinaService(MaquinaRepository maquinaRepository, ObtenerUbicacionService obtenerUbicacionService) {
        this.maquinaRepository = maquinaRepository;
        this.obtenerUbicacionService = obtenerUbicacionService;
    }

    public void crear(MaquinaRequest request) {
        Ubicacion ubicacion = obtenerUbicacionService.obtener(request.ubicacionId);
        maquinaRepository.save(new Maquina(
                request.tipo,
                new NombreMaquina(request.nombre),
                ubicacion
        ));
    }
}