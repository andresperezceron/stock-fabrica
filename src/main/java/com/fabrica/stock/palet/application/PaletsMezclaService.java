package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaletsMezclaService {
    private final PaletRepository paletRepository;

    public PaletsMezclaService(PaletRepository paletRepository) {
        this.paletRepository = paletRepository;
    }

    public List<Palet> obtener() {
        return paletRepository.findAllByEstado(EstadoPalet.VALIDADO)
                .stream()
                .filter(Palet::estoyEstacionConsumoInyeccion)
                .toList();
    }
}
