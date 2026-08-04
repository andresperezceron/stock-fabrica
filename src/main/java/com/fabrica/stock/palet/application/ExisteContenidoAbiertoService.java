package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import org.springframework.stereotype.Service;

@Service
public class ExisteContenidoAbiertoService {
    private final PaletRepository paletRepository;

    public ExisteContenidoAbiertoService(PaletRepository paletRepository) {
        this.paletRepository = paletRepository;
    }

    public boolean existe(Maquina maquina) {
        return paletRepository.existsByMaquinaAndEstado(maquina, EstadoPalet.ABIERTO);
    }
}