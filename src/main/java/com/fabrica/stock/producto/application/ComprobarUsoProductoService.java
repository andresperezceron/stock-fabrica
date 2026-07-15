package com.fabrica.stock.producto.application;

import com.fabrica.stock.contenidopalet.infrastructure.ContenidoPaletRepository;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ComprobarUsoProductoService {
    private final MovimientoStockRepository movimientoStockRepository;
    private final ContenidoPaletRepository contenidoPaletRepository;

    public ComprobarUsoProductoService(MovimientoStockRepository movimientoStockRepository,
                                       ContenidoPaletRepository contenidoPaletRepository) {
        this.movimientoStockRepository = movimientoStockRepository;
        this.contenidoPaletRepository = contenidoPaletRepository;
    }

    public boolean tieneUso(Producto producto) {
        return movimientoStockRepository.existsByProducto(producto) ||
                contenidoPaletRepository.existsByProducto(producto);
    }
}