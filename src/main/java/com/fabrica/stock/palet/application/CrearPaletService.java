package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.domain.ConfiguracionPalet;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.TipoPalet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.stereotype.Service;

@Service
public class CrearPaletService {
    private final PaletRepository paletRepository;

    public CrearPaletService(PaletRepository paletRepository) {
        this.paletRepository = paletRepository;
    }

    public Palet crear(Ubicacion ubicacion, TipoPalet tipoPalet) {
        return paletRepository.save(new Palet(
                ubicacion,
                tipoPalet,
                ConfiguracionPalet.AUTOMATICO,
                EstadoPalet.ABIERTO));
    }
}