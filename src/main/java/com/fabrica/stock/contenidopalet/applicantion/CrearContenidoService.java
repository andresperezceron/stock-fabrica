package com.fabrica.stock.contenidopalet.applicantion;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.contenidopalet.infrastructure.ContenidoPaletRepository;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CrearContenidoService {
    private final ContenidoPaletRepository contenidoPaletRepository;

    public CrearContenidoService(ContenidoPaletRepository contenidoPaletRepository) {
        this.contenidoPaletRepository = contenidoPaletRepository;
    }

    public ContenidoPalet crear(Palet palet, Producto producto, Lote lote, BigDecimal cantidad, LocalDateTime fecha) {
        ContenidoPalet contenido =  new ContenidoPalet(palet, producto, lote, cantidad, fecha);
        palet.incorporarContenidoPalet(contenido);
        return contenidoPaletRepository.save(contenido);
    }
}