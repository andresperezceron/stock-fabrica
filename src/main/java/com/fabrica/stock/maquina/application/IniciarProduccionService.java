package com.fabrica.stock.maquina.application;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.estacion.domain.Estaciones;
import com.fabrica.stock.estacion.domain.exceptions.EstacionNoEncontradaException;
import com.fabrica.stock.estacion.infrastructure.EstacionRepository;
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
    private final EstacionRepository estacionRepository;

    public IniciarProduccionService(ObtenerMaquinaService obtenerMaquinaService,
                                    PaletRepository paletRepository,
                                    EstacionRepository estacionRepository)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.paletRepository = paletRepository;
        this.estacionRepository = estacionRepository;
    }

    @Transactional
    public void iniciar(IniciarProduccionRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(request.maquinaId());

        if(maquina.estaProduccion()) throw new EstadoMaquinaNoValidoException("Ya hay una producción activa");
        else if(!maquina.estaConfigurada()) throw new EstadoMaquinaNoValidoException("No se puede iniciar la " +
                "producción si no está configurada: " + maquina.getEstado());

        Estacion estacionProduccion = estacionRepository
                .findByNombre(Estaciones.PRODUCCION)
                .orElseThrow(() -> new EstacionNoEncontradaException("Fallo de sistema."));

        paletRepository.save(new Palet(maquina, request.tipoPalet(), estacionProduccion));
        maquina.enProduccion();
    }
}