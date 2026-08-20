package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ReanudarProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;

    public ReanudarProduccionService(ObtenerMaquinaService obtenerMaquinaService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    @Transactional
    public void reanudar(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Palet palet = maquina.getPalet();

        if(!maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(palet.estoyAbierto())
            throw new EstadoNoValidoException("Palet no está cerrado.");

        if(palet.soyTipoGranel())
            throw new ConflictException("Solo un registro en tipo granel.");

        if(!maquina.getConfigProduccion().esValida())
            throw new ConflictException("Configuración producción no válida.") ;

        palet.abrir();
    }
}