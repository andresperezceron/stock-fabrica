package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.exceptions.EstadoMaquinaNoValidoException;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.TipoPalet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import org.springframework.stereotype.Service;

@Service
public class IniciarProduccionGranelService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final PaletRepository paletRepository;

    public IniciarProduccionGranelService(ObtenerMaquinaService obtenerMaquinaService,
                                          PaletRepository paletRepository)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.paletRepository = paletRepository;
    }

    public void iniciar(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        if(!maquina.estaConfigurada()) {
            throw new EstadoMaquinaNoValidoException("No se puede iniciar la producción con la maquina en estado: " +
                    maquina.getEstado());
        }
        paletRepository.save(new Palet(maquina, TipoPalet.GRANEL));
        maquina.enProduccion();
    }
}