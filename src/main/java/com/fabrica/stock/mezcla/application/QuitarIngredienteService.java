package com.fabrica.stock.mezcla.application;

import com.fabrica.stock.lote.application.ObtenerLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.mezcla.api.dto.IdLoteIngrediente;
import com.fabrica.stock.mezcla.domain.Mezcla;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class QuitarIngredienteService {
    private final ObtenerMezclaService obtenerMezclaService;
    private final ObtenerLoteService obtenerLoteService;

    public QuitarIngredienteService(ObtenerMezclaService obtenerMezclaService,
                                    ObtenerLoteService obtenerLoteService)
    {
        this.obtenerMezclaService = obtenerMezclaService;
        this.obtenerLoteService = obtenerLoteService;
    }

    @Transactional
    public void quitar(Long mezclaId, IdLoteIngrediente request) {
        Mezcla mezcla = obtenerMezclaService.obtener(mezclaId);
        Lote lote = obtenerLoteService.obtener(request.idLoteIngrediente());

        if(!mezcla.contieneMateriaPrima(lote))
            throw new RecursoNoEncontradoException("Ingrediente no encontrado.");

        mezcla.quitarIngrediente(lote);
    }
}