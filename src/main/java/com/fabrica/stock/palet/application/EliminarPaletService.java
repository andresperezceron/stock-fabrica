package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class EliminarPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final PaletRepository paletRepository;

    public EliminarPaletService(ObtenerPaletService obtenerPaletService,
                                PaletRepository paletRepository)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.paletRepository = paletRepository;
    }

    public void eliminar(Long paletId) {
        Palet palet = obtenerPaletService.obtener(paletId);

        if(!palet.estoyValidado() && !palet.estoyPendienteValidacion())
            throw new EstadoNoValidoException("Servicio no disponible.");
    }
}
