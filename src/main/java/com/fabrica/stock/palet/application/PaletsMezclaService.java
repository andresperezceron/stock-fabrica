package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.api.dto.response.PaletsMezclaResponse;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import org.springframework.stereotype.Service;

@Service
public class PaletsMezclaService {
    private final PaletRepository paletRepository;

    public PaletsMezclaService(PaletRepository paletRepository) {
        this.paletRepository = paletRepository;
    }

    public PaletsMezclaResponse obtener() {
        return PaletsMezclaResponse.of(paletRepository.findAllByEstado(EstadoPalet.VALIDADO)
                .stream()
                .filter(Palet::estoyEstacionConsumoInyeccion)
                .toList());
    }
}