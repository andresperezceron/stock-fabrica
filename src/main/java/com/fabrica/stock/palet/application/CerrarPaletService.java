package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CerrarPaletService {
    @Transactional
    public void cerrar(Palet palet) {
        if(!palet.estaAbierto()) {
            throw new EstadoPaletInvalidoException("El palet no esta abierto");
        }
        palet.setEstado(EstadoPalet.CERRADO);
    }
}
