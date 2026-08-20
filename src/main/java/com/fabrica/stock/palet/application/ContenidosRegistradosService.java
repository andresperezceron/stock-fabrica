package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.api.dto.response.ContenidosRegistradosResponse;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class ContenidosRegistradosService {
    private final ObtenerMaquinaService obtenerMaquinaService;

    public ContenidosRegistradosService(ObtenerMaquinaService obtenerMaquinaService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    public ContenidosRegistradosResponse obtener(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Palet palet = maquina.getPalet();

        if(!maquina.estaEnProduccion()) throw new EstadoNoValidoException("Servicio no disponible.");
        if(palet.soyTipoGranel()) throw new RecursoNoValidoException("Servicio no disponible.");

        return ContenidosRegistradosResponse.of(palet);
    }
}