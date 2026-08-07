package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.TipoPalet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import org.springframework.stereotype.Service;

@Service
public class ComprobarTipoPaletGranelService {
    private final PaletRepository paletRepository;

    public ComprobarTipoPaletGranelService(PaletRepository paletRepository) {
        this.paletRepository = paletRepository;
    }

    public boolean comprobar(Maquina maquina) {
        return paletRepository.existsByMaquinaAndTipo(maquina, TipoPalet.GRANEL);
    }
}
