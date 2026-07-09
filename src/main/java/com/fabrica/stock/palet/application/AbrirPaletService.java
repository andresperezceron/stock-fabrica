package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class AbrirPaletService {
    public void abrir(Palet palet) {
        if(!palet.estaCerrado()) {
            throw new EstadoPaletInvalidoException("El palet no esta cerrado");
        }
        palet.setEstado(EstadoPalet.ABIERTO);
    }
}