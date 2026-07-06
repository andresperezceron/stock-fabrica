package com.fabrica.stock.contenidopalet.applicantion;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.contenidopalet.infrastructure.ContenidoPaletRepository;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.palet.domain.Palet;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CrearContenidoService {
    private final ContenidoPaletRepository contenidoPaletRepository;

    public CrearContenidoService(ContenidoPaletRepository contenidoPaletRepository) {
        this.contenidoPaletRepository = contenidoPaletRepository;
    }

    public ContenidoPalet crear(Palet palet, Lote lote, BigDecimal cantidad) {
        return contenidoPaletRepository.save(new ContenidoPalet(palet, lote, cantidad));
    }
}
