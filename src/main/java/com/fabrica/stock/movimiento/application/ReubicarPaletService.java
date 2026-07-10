package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ReubicarPaletService {
    private final MovimientoStockRepository movimientoStockRepository;

    public ReubicarPaletService(MovimientoStockRepository movimientoStockRepository) {
        this.movimientoStockRepository = movimientoStockRepository;
    }

    @Transactional
    public void reubicar(Palet palet, Ubicacion destino) {
        Ubicacion origen = palet.getUbicacion();
        LocalDateTime fecha = LocalDateTime.now();
        movimientoStockRepository.saveAll(palet.getContenidos()
                .stream()
                .map(contenido -> new MovimientoStock(
                        contenido.getProducto(),
                        contenido.getLote(),
                        TipoMovimiento.SALIDA,
                        contenido.getCantidad(),
                        fecha,
                        origen,
                        destino))
                .toList()
        );
        palet.setUbicacion(destino);
    }
}