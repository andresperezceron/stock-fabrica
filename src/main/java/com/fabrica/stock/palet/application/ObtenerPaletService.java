package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.PaletNoEncontradoException;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerPaletService {
    private final PaletRepository paletRepository;

    public ObtenerPaletService(PaletRepository paletRepository) {
        this.paletRepository = paletRepository;
    }

    public Palet obtener(Long id){
        return paletRepository.findById(id).orElseThrow(() -> new PaletNoEncontradoException("Palet no encontrado."));
    }
}