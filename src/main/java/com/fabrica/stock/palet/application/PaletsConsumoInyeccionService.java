package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.api.dto.response.PaletsConsumoInyeccionResponse;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import org.springframework.stereotype.Service;

@Service
public class PaletsConsumoInyeccionService {
    private final PaletRepository paletRepository;

    public PaletsConsumoInyeccionService(PaletRepository paletRepository) {
        this.paletRepository = paletRepository;
    }

    public PaletsConsumoInyeccionResponse listar() {
        return PaletsConsumoInyeccionResponse.of(paletRepository
                .findAllByEstado(EstadoPalet.VALIDADO)
                .stream()
                .filter(Palet::soyConsumoInyeccion).toList());
    }
}