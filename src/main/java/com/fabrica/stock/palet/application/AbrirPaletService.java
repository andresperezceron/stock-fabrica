package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AbrirPaletService {
    private final ObtenerPaletService obtenerPaletService;

    public AbrirPaletService(ObtenerPaletService obtenerPaletService) {
        this.obtenerPaletService = obtenerPaletService;
    }

    @Transactional
    public void abrir(Long idPalet) {
        Palet palet = obtenerPaletService.obtener(idPalet);
        if(!palet.estaCerrado()) {
            throw new EstadoPaletInvalidoException("El palet no esta cerrado");
        }
        palet.setEstado(EstadoPalet.ABIERTO);
    }
}