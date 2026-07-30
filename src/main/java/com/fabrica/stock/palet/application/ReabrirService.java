package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class ReabrirService {
    private final ObtenerPaletService obtenerPaletService;

    public ReabrirService(ObtenerPaletService obtenerPaletService,
                          ObtenerMaquinaService obtenerMaquinaService)
    {
        this.obtenerPaletService = obtenerPaletService;
    }

    public void reabrir(Long idPalet, Long idMaquina) {
        Palet palet = obtenerPaletService.obtener(idPalet);
        if(!palet.estaCerrado()) throw new EstadoPaletInvalidoException("El palet no está cerrado.");
    }
}
