package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.maquina.api.dto.response.VerConfigProduccionResponse;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import org.springframework.stereotype.Service;

@Service
public class VerConfigProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;

    public VerConfigProduccionService(ObtenerMaquinaService obtenerMaquinaService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    public VerConfigProduccionResponse ver(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);

        if(maquina.noTengoConfigProduccion())
            throw new ConflictException("Servicio no disponible.");

        return VerConfigProduccionResponse.of(maquina);
    }
}