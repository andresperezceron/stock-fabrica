package com.fabrica.stock.producto.application;

import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ComprobarUsoProductoService {
    private final LoteRepository loteRepository;

    public ComprobarUsoProductoService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public boolean tieneUso(Producto producto) {
        return loteRepository.existsByProducto(producto);
    }
}