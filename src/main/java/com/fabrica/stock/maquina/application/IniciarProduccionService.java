package com.fabrica.stock.maquina.application;

import com.fabrica.stock.estacion.application.ObtenerEstacionService;
import com.fabrica.stock.maquina.api.dto.request.IniciarProduccionRequest;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class IniciarProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerEstacionService obtenerEstacionService;
    private final PaletRepository paletRepository;

    public IniciarProduccionService(ObtenerMaquinaService obtenerMaquinaService,
                                    ObtenerEstacionService obtenerEstacionService,
                                    PaletRepository paletRepository)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerEstacionService = obtenerEstacionService;
        this.paletRepository = paletRepository;
    }

    @Transactional
    public void iniciar(Long maquinaId, IniciarProduccionRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);

        if(maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(!maquina.estaConfigurada())
            throw new EstadoNoValidoException("Máquina no configurada.");

        Palet palet = paletRepository.save(new Palet(
                request.tipoPalet(),
                maquina.getConfigProduccion().getProducto(),
                obtenerEstacionService.obtenerProduccion()));

        maquina.asignarPalet(palet);
    }
}