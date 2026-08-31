package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.api.dto.response.TerminalResponse;
import com.fabrica.stock.maquina.domain.Maquina;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {
    private final ObtenerMaquinaService obtenerMaquinaService;

    public TerminalService(ObtenerMaquinaService obtenerMaquinaService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    public TerminalResponse obtener(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        return TerminalResponse.of(maquina);
    }
}