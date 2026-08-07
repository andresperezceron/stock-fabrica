package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import org.springframework.stereotype.Service;

@Service
public class ComprobarPaletAbiertoService {
    private final PaletRepository paletRepository;

    public ComprobarPaletAbiertoService(PaletRepository paletRepository) {
        this.paletRepository = paletRepository;
    }

    public boolean comprobar(Maquina maquina) {
        return paletRepository.existsByMaquinaAndEstado(maquina, EstadoPalet.ABIERTO);
    }
}