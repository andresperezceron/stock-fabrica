package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.api.dto.IniciarProduccionRequest;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.exceptions.EstadoMaquinaNoValidoException;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class IniciarProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final PaletRepository paletRepository;
    public IniciarProduccionService(ObtenerMaquinaService obtenerMaquinaService, PaletRepository paletRepository) {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.paletRepository = paletRepository;
    }

    @Transactional
    public void iniciar(IniciarProduccionRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(request.maquinaId());

        if(maquina.estaProduccion()) throw new EstadoMaquinaNoValidoException("Ya hay una producción activa");
        else if(!maquina.estaConfigurada()) throw new EstadoMaquinaNoValidoException("No se puede iniciar la " +
                "producción si no está configurada: " + maquina.getEstado());

        paletRepository.save(new Palet(maquina, request.tipoPalet()));
        maquina.enProduccion();
    }
}