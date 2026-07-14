package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CambiarEstadoPaletService {
    @Transactional
    public void abrir(Palet palet) {
        if(!palet.estaCerrado()) {
            throw new EstadoPaletInvalidoException("El palet no esta cerrado");
        }
        palet.setEstado(EstadoPalet.ABIERTO);
    }

    @Transactional
    public void cerrar(Palet palet) {
        if(!palet.estaAbierto()) {
            throw new EstadoPaletInvalidoException("El palet no esta abierto");
        }
        palet.setEstado(EstadoPalet.CERRADO);
    }

    @Transactional
    public void finalizar(Palet palet) {
        if(!palet.estaCerrado()) {
            throw new EstadoPaletInvalidoException("El palet debe estar cerrado para poder finalizarlo.");
        }
        palet.setEstado(EstadoPalet.FINALIZADO);
    }
}
