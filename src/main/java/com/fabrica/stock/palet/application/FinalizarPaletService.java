package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FinalizarPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final PaletRepository paletRepository;

    public FinalizarPaletService(ObtenerPaletService obtenerPaletService,
                                 PaletRepository paletRepository)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.paletRepository = paletRepository;
    }

    @Transactional
    public void finalizar(Long id) {
        Palet palet = obtenerPaletService.obtener(id);
        if(!palet.estaCerrado()) throw new EstadoPaletInvalidoException("El palet no está cerrado.");

        if(palet.estaVacio()) paletRepository.delete(palet);
        else palet.cambiarAPendienteValidacion();

        //falta realizar el primer movimiento con destino VALIDACION ...
    }
}