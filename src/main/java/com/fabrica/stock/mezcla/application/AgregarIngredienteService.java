package com.fabrica.stock.mezcla.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.mezcla.api.dto.IdPaletIngrediente;
import com.fabrica.stock.mezcla.domain.Mezcla;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AgregarIngredienteService {
    private final ObtenerMezclaService obtenerMezclaService;
    private final ObtenerPaletService obtenerPaletService;

    public AgregarIngredienteService(ObtenerMezclaService obtenerMezclaService,
                                     ObtenerPaletService obtenerPaletService)
    {
        this.obtenerMezclaService = obtenerMezclaService;
        this.obtenerPaletService = obtenerPaletService;
    }

    @Transactional
    public void agregar(Long mezclaId, IdPaletIngrediente request) {
        Mezcla mezcla = obtenerMezclaService.obtener(mezclaId);
        Palet palet = obtenerPaletService.obtener(request.idPaletIngrediente());
        Lote lote = palet.getContenidoGranel().getLote();

        if(!palet.soyConsumoInyeccion())
            throw new RecursoNoValidoException("Palet no valido.");

        if(!palet.estoyEstacionConsumoInyeccion())
            throw new RecursoNoValidoException("El palet no está en consumo inyección.");

        if(mezcla.contieneMateriaPrima(lote))
            throw new RecursoNoValidoException("Lote ya está agregado.");

        mezcla.agregarIngrediente(lote);
    }
}