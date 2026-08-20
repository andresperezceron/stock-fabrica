package com.fabrica.stock.palet.application;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.contenidopalet.infrastructure.ContenidoPaletRepository;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.api.dto.response.ContenidoRegistradoResponse;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class ContenidoRegistradoService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ContenidoPaletRepository contenidoPaletRepository;

    public ContenidoRegistradoService(ObtenerMaquinaService obtenerMaquinaService,
                                      ContenidoPaletRepository contenidoPaletRepository)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.contenidoPaletRepository = contenidoPaletRepository;
    }

    public ContenidoRegistradoResponse obtener(Long maquinaId, Long contenidoId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Palet palet = maquina.getPalet();
        ContenidoPalet contenido = contenidoPaletRepository.findById(contenidoId).orElse(null);

        if(!maquina.estaEnProduccion()) throw new EstadoNoValidoException("Servicio no disponible.");
        if(palet.soyTipoGranel()) throw new RecursoNoValidoException("Servicio no disponible.");
        if(contenido == null) throw new RecursoNoEncontradoException("Contenido no encontrado.");
        if(!contenido.esMiPalet(palet)) throw  new RecursoNoValidoException("Contenido no pertenece a palet.");

        return ContenidoRegistradoResponse.of(contenido);
    }
}