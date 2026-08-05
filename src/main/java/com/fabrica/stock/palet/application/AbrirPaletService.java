package com.fabrica.stock.palet.application;

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
    public void abrir(Long id) {
        Palet palet = obtenerPaletService.obtener(id);
        if(!palet.estaCerrado()) throw new EstadoPaletInvalidoException("El palet no está cerrado.");
        else if(palet.procesoEstaPausado()) throw new EstadoPaletInvalidoException("La configuracion actual de produccion no es valida.") ;
        palet.abrir();
    }
}